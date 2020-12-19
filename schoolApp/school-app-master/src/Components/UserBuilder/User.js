import React, { Component } from 'react';
import UserDataService from '../../api/UserDataService';
import Button from '../../Button/Button'


import classes from './User.module.css';

class User extends Component {
    state = {
        user: null,
        role: null
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

    }
    render() {
        let userData = null;

        console.log(this.state.user)
        if (this.state.user != null) {
            userData = (
                <div>
                    <ul class="list-group">
                        <li class="list-group-item">Vorname : {this.state.user.firstName}</li>
                        <li class="list-group-item">Nachname : {this.state.user.lastName}</li>
                        <li class="list-group-item">Email Adresse : {this.state.user.email}</li>
                        <li class="list-group-item">Geburtsdatum {this.state.user.birthDate}</li>
                        <li class="list-group-item"></li>
                    </ul>

                </div>

            )

        }




        return (
            <>

                <div className={classes.User}>

                    <h2>{this.state.role}</h2>

                    {userData}







                </div>

                <div className={classes.User}>

                    <Button clicked={this.delteclicked} btnType="Danger"> Delete</Button>
                    <Button clicked={this.props.cancel} btnType="Success"> approve </Button>
                    <Button clicked={this.showClicked} btnType="Success"> Anzeigen </Button>



                </div>
            </>


        )

    }
}


export default User;