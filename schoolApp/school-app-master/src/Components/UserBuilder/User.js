import React, { Component } from 'react';
import UserDataService from '../../api/UserDataService';
import Button from '../../Button/Button'
import ProfileForm from './profileForm';
import axios from 'axios'


import classes from './User.module.css';

class User extends Component {
    state = {
        user: null,
        role: null,
        showForm: false,
        usedEmails : [],
        successMessage : null

    }

    componentDidMount() {
        const id = this.props.match.params.id
        console.log(id)
        UserDataService.getUserById(id)
            .then(response => {
                this.setState({
                    user: response.data,
                    role: response.data.role
                })
                console.log(response.data)
            })
            let usedEmails = []
        axios.get(`http://localhost:8080/users/existingEmails`)
        .then(response => {
            console.log(response.data)
            usedEmails = response.data
            this.setState({usedEmails : usedEmails})
        })
        .catch(err => {
            console.log(err)
        })
    }

    approve = () => {
        const id = this.props.match.params.id
        UserDataService.approve(id)
            .then(response => {
                console.log('user with id' + id + 'is approved')
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })

    }

    disApprove = () => {
        const id = this.props.match.params.id
        UserDataService.disApprove(id)
            .then(response => {
                console.log('user with id' + id + 'is disapproved')
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })

    }

    showForm = () => {
        if (!this.state.showForm) {
            this.setState({ showForm: true })
        } else {
            this.setState({ showForm: false })
        }

    }

    updateUser=(updatedUser)=> {
        this.setState({user : updatedUser,
            successMessage : <div class="alert alert-success" role="alert">
            <strong>Ihre Daten wurden erfolgreich </strong> 
        </div>
        
        })
        this.showForm();
        sessionStorage.setItem('firstName' , updatedUser.firstName)
        sessionStorage.setItem('lastName' , updatedUser.lastName)
        


    }
    render() {
        let userData = null;
        if (this.state.user != null) {
            userData = (
                <div>
                    <ul class="list-group">
                        <li class="list-group-item font-weight-bold">Vorname : {this.state.user.firstName}</li>
                        <li class="list-group-item font-weight-bold">Nachname : {this.state.user.lastName}</li>
                        <li class="list-group-item font-weight-bold">Email Adresse : {this.state.user.email}</li>
                        <li class="list-group-item font-weight-bold">Geburtsdatum {this.state.user.birthDate}</li>
                        <li class="list-group-item font-weight-bold"></li>
                    </ul>

                </div>

            )

        }

        let controls = null
        if (sessionStorage.getItem('role') === 'admin') {
            controls =
                <div className={classes.User}>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <Button clicked={this.delteclicked} btnType="Danger"> Delete</Button>
                        <Button clicked={this.disApprove} btnType="Danger"> Disapprove</Button>
                        
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <Button clicked={this.approve} btnType="Success"> approve </Button>
                        <Button clicked={this.showClicked} btnType="Success"> Bearbeiten </Button>
                    </div>
                </div>

        } else {
            controls = this.state.user && <Button clicked={this.showForm} btnType="Success"> Bearbeiten </Button>

        }

        let form = 
        <div >
            <ProfileForm updateUser={this.updateUser
            } usedEmails={this.state.usedEmails} user={this.state.user}></ProfileForm>
        </div>





        return (
            <>

                <div className={classes.User}>

                    <h2>{this.state.role}</h2>

                    {this.state.successMessage}

                    {userData}

                    {controls}

                    {this.state.showForm && form}









                </div>


            </>


        )

    }
}


export default User;