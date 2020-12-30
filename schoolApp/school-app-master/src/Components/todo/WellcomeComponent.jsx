import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import HelloWorldService from '../../api/HelloWorldService.js'
import UserDataService from '../../api/UserDataService.js'
import styles from '../../Styles.module.css'
import HeaderComponent from './HeaderComponent'
import Button from '../../Button/Button'


class WellcomeComponent extends Component {

    
        state = {
            user : null,
            approved : null,
            role : null,
        }
    

    componentDidMount() {
        UserDataService.getUserById(this.props.match.params.id)
        .then(response => {
            console.log(response.data)
            this.setState({
                user : response.data,
                approved : response.data.approved,
                role : response.data.role
            })
        })
        .catch(error => {
            console.log(error.response.data.message)
        })

    }




    render() {
        let errorMessage = null
        if (this.state.user) {
            if((!this.state.approved) && (sessionStorage.getItem('role')!== 'admin')) 
            errorMessage = 
                <div class="alert alert-secondary" role="alert">
    <strong>Sie sind noch nicht von einem Administrator bestaetigt, haben Sie bitte etwas Geduld.</strong> 
</div>
        }

        let wellcomeMessage = 
        
            
            <h1 className={styles.Wellcome}>Willkommen {sessionStorage.getItem('firstName')} {sessionStorage.getItem('lastName')}.</h1>
        
        return (
            <>
            <div>
                
                {wellcomeMessage}
                {errorMessage}



                <p class="font-weight-bolder">Verwalte dein Profil</p>
                {this.state.user && <Link to={{
                                    pathname : '/verwalten/anzeigen/' + this.state.user.id
                                }
                                }><Button btnType="Success" >Hier</Button>
                                </Link> } 
                 
       
            </div>

            </>

        )
    }

}

export default WellcomeComponent 