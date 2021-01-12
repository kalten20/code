import React, { Component } from 'react'
import ClassesDataService from '../../api/ClassesDataService'
import ClassInbox from '../AnnouncementsBuilder/ClassInbox'
import Inbox from '../AnnouncementsBuilder/Inbox'

class MyClass extends Component {

    state = {
        userId: sessionStorage.getItem('id'),
        myClass: {
            id: 0,
            className: '',
            teachers: [],
            students: [],
            inboxId: 0
        },
        showTeachers : false,
        showStudents : false,
        showNews : false
    }

    componentDidMount() {
        ClassesDataService.getMyClass(this.props.match.params.classId)
            .then(response => {
                console.log(response.data)
                this.setState({ myClass: response.data })
            }).catch(error => {
                console.log(error)
            })
    }

    toggleTeachers =()=>{
        this.setState({showTeachers : !this.state.showTeachers})
    }
    toggleStudents =()=>{
        this.setState({showStudents : !this.state.showStudents})
    }
    toggleNews =()=>{
        this.setState({showNews : !this.state.showNews})
    }

   
        timeTable=()=> {
            this.props.history.push(`/timetable/${this.props.match.params.classId}`)
        }
    

    render() {

        //creting teacher table
        let teacherBody = null
        if (this.state.myClass) {
            teacherBody =
                <tbody>
                    {
                        this.state.myClass.teachers.map((teacher, index) => {
                            return (
                                <tr key={teacher.id}>
                                    <th scope="row">{teacher.id}</th>
                                    <td>{teacher.firstName}</td>
                                    <td>{teacher.lastName}</td>
                                    <td>{teacher.lastName}</td>
                                    <td>{teacher.lastName}</td>
                                    <td><button className="btn btn-warning">action</button></td>
                                </tr>
                            )
                        })
                    }

                </tbody>

        }
        let teacherTable =
            <div>
                <h3>Lehrende</h3>
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

                    {teacherBody}

                </table>

            </div>



        //CREATING STUDENT TABLE

        let studentBody = null
        if (this.state.myClass) {
            studentBody =
                <tbody>
                    {
                        this.state.myClass.students.map((student, index) => {
                            return (
                                <tr key={student.id}>
                                    <th scope="row">{student.id}</th>
                                    <td>{student.firstName}</td>
                                    <td>{student.lastName}</td>
                                    <td>{student.lastName}</td>
                                    <td>{student.lastName}</td>
                                    <td><button className="btn btn-warning">action</button></td>
                                </tr>
                            )
                        })


                    }

                </tbody>
        }

        let studentTable =
            <div>
                <h3>Lernende</h3>

                <table class="table table-bordered table-striped">
                    <thead class="thead-light">
                        <tr>
                            <th>:)</th>
                            <th>Vorname</th>
                            <th>Nachname</th>
                            <th>Durschnitt</th>
                            <th>Profil</th>
                        </tr>
                    </thead>
                    {studentBody}
                </table>

            </div>

        //*********************** */

        return (
            <div>
                <div className ="card">
                    <div className="card-body">
                    <h4 class="card-title">Klasse {this.state.myClass.className}</h4>
                    <button type="button" class="btn btn-dark btn-sm" onClick={this.toggleTeachers}>Lehrer</button>  
                    <button type="button" class="btn btn-warning btn-sm" onClick={this.toggleStudents}>Lernende</button>  
                    <button type="button" class="btn btn-dark btn-sm" onClick={this.toggleNews}>News</button>  
                    <button type="button" class="btn btn-warning btn-sm" >Tests</button>  
                    <button type="button" class="btn btn-dark btn-sm" onClick={this.timeTable}>Plan</button>
                    </div>
                </div>
                {this.state.showTeachers && teacherTable}
                {this.state.showStudents && studentTable}
                {this.state.showNews && <ClassInbox classId={this.props.match.params.classId} />
}
            </div>
        )
    }
}
export default MyClass