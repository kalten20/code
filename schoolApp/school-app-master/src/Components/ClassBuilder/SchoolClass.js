import React, { Component } from 'react';
import { Link, Route, withRouter } from 'react-router-dom';


import classes from './SchoolClass.module.css';
import Button from '../../Button/Button'
import UserDataService from '../../api/UserDataService';
import ClassesDataService from '../../api/ClassesDataService';

import AddToClass from './AddToClass'

class SchoolClass extends Component {

    state = {
        class : null,
        name : '',
        teachers : [],
        students : [],
        showAddTeacher : false,
        showAddStudent : false,
        usedTeacherEmails : ['a'],
        usedStudentEmails : ['b'],
        presentTeacherEmails : [],
        classTeacherEmails : [],
        globalTeacherEmails : [],
        classStudentEmails : [],
        globalStudentEmails : [],
        unavailableStudents : [],
        deleteClicked : false  
    }

    componentDidMount () {
        let name = this.props.match.params.name
        this.setState({name : name})
        ClassesDataService.getClass(name)
        .then(response =>{
            console.log(response.data)
            this.setState({class : response.data,
                teachers : response.data.teachers,
                students : response.data.students
            })
            console.log(response.data)
            this.populatePossibleValues(response.data);
        })
        .catch(error=> {
            console.log(error)
        })

        

    }

    populatePossibleValues =(loadedClass) =>{
        this.populatePossibleTeacherEmails(loadedClass.teachers)
        this.populatePossibleStudentEmails(loadedClass.students)

    }
    populatePossibleTeacherEmails=(teachers)=>{
        //ALL EMAILS of teachers that exist
        UserDataService.getAllEmailsByRole('teacher')
        .then(response => {
            this.setState({globalTeacherEmails : response.data})
        })
        .catch(error =>{
            console.log(error)
        })


        //EMails of teachers that are in this class already
        let classTeacherEmails = []
        if(teachers) {
            teachers.forEach(element => {
                classTeacherEmails.push(element.email)
                });
                this.setState({classTeacherEmails : classTeacherEmails})

        }
    
    }


    populatePossibleStudentEmails=(students)=>{
        //ALL STUDENT EMAILS
        UserDataService.getAllEmailsByRole('student')
        .then(response => {
            this.setState({globalStudentEmails : response.data})
        }).catch(error =>{
            console.log(error)
        })

        //Student emails that have already a class
        ClassesDataService.getUnavailableStudentEmails()
        .then(response => {
            this.setState({unavailableStudents : response.data})
        }).catch(error =>{
            console.log(error)
        })

        //Students mails that are is this class
        let classStudentEmails = []
        if(students) {
            students.forEach(element => {
                classStudentEmails.push(element.email)
                });
                this.setState({classStudentEmails : classStudentEmails})
        }
    


    }

    toggleAddTeacher=()=> {
        if (!this.state.showAddTeacher) {
            this.setState({ showAddTeacher: true })
        } else {
            this.setState({ showAddTeacher: false })
        }
    }

    toggleAddStudent=()=> {
        if (!this.state.showAddStudent) {
            this.setState({ showAddStudent: true })
        } else {
            this.setState({ showAddStudent: false })
        }
    }

    updateTeachers =(teacher)=> {
        let teachers = this.state.teachers;
        teachers.unshift(teacher)
        this.setState({teachers : teachers})
    }
    updateStudents =(student)=> {
        console.log('student')
        let students = this.state.students;
        students.unshift(student);
        this.setState({students : students})
        console.log(this.state.students)
    }

    notifyDeleteClicked =()=> {

    }


    deleteTeacherClicked=(id, index, email)=>{
        let teachers = this.state.teachers;
        teachers.splice(index,1);
        this.setState({teachers : teachers})

        let classTeacherEmails = this.state.classTeacherEmails;
        classTeacherEmails.splice(classTeacherEmails.indexOf(email),1)
        this.setState({classTeacherEmails : classTeacherEmails})

        this.resetDeleteClicked()


        

        ClassesDataService.removeElement(this.state.name,'teacher',id)
        .then(response => {
            console.log(response.data)

        })
        .catch(error =>{
            console.log(error);
        })

    }

    deleteStudentClicked =(id,index, email)=> {
        let students = this.state.students
        students.splice(index,1);
        this.setState({students : students})

        this.resetDeleteClicked()


        let classStudentEmails = this.state.classStudentEmails;
        classStudentEmails.splice(classStudentEmails.indexOf(email),1)
        this.setState({classStudentEmails : classStudentEmails})

        ClassesDataService.removeElement(this.state.name ,'student',id)
        .then(response => {
            console.log(response.data)

        })
        .catch(error =>{
            console.log(error);
        })
    }
    
    resetDeleteClicked =() => {
        let deleteClicked = this.state.deleteClicked
        this.setState({deleteClicked : !deleteClicked})
    }

    render() {

        let addTeacher = 
        <div >
            <AddToClass 
            deleteClicked={this.state.deleteClicked}
            resetDeleteClicked={this.resetDeleteClicked}
             updateParent={this.updateTeachers}  targetClass={this.state.name} 
             toAdd="teacher" globalPossibleValues={this.state.globalTeacherEmails} alreadyInClass={this.state.classTeacherEmails}
             >

             </AddToClass>
            

        </div>
        let addStudent = 
        <div >
            <AddToClass 
            deleteClicked={this.state.deleteClicked}
            resetDeleteClicked={this.resetDeleteClicked}
            updateParent={this.updateStudents}  targetClass={this.state.name} 
            toAdd="student" globalPossibleValues={this.state.globalStudentEmails} alreadyInClass={this.state.classStudentEmails}
            unavailable={this.state.unavailableStudents}

            >

            </AddToClass>

        </div>

        return (
            <>

        <h2 class="font-weight-bolder">{this.state.name}</h2>

                {/* LEHRER */}

                <div className={classes.SchoolClasses}>
                <p class="font-weight-bold">Lehrer</p>
        <Button btnType="Success" clicked={this.toggleAddTeacher}>Hinzufügen </Button>
        {this.state.showAddTeacher && addTeacher}
        

    <table class="table table-bordered table-striped">
                        <thead class="thead-light">
                            <tr>
                                <th>:)</th>
                                <th>Vorname</th>
                                <th>Nachname</th>
                                <th>Fach</th>
                                <th>Profil</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.teachers &&
                                this.state.teachers.map((teacher,index) => {
                                    return(
                                    <tr key={teacher.id}>
                                        <th scope="row">{teacher.id}</th>
                                <td>{teacher.firstName}</td>
                                        <td>{teacher.lastName}</td>
                                        <td>{teacher.lastName}</td>
                                        <td>{teacher.lastName}</td>
                                        <td><button onClick={() => this.deleteTeacherClicked(teacher.id, index, teacher.email)} className="btn btn-warning">Löschen</button></td>


                                    </tr>)
                                    
                                })
                            }
                        </tbody>
                    </table>
                </div>





                {/* Schueler */}
                <div className={classes.SchoolClasses}>
                <p class="font-weight-bold">Studenten</p>
                <Button btnType="Success" clicked={this.toggleAddStudent}>Hinzufügen</Button>
                {this.state.showAddStudent && addStudent}
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>;D</th>
                                <th>Vorname</th>
                                <th>Nachname</th>
                                <th>Profil</th>
                                <th>Noten</th>

                            </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.students &&
                        this.state.students.map((student, index) => {
                                    return(
                                    <tr key={student.id}>
                                        <th scope="row">{student.id}</th>
                                <td>{student.firstName}</td>
                                        <td>{student.lastName}</td>
                                        <td>{student.lastName}</td>
                                        <td>{student.lastName}</td>
                                        <td><button onClick={() => this.deleteStudentClicked(student.id, index, student.email)} className="btn btn-warning">Löschen</button></td>


                                    </tr>)
                                    
                                })
                            }
                        </tbody>
                    </table>

                </div>

            </>
        )
    }
}
export default SchoolClass