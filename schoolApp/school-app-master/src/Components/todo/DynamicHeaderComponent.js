import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import { withRouter } from 'react-router';
import AuthenticationService from './AuthenticationService'
import { FaHome } from "react-icons/fa";
import { FaUniversity } from "react-icons/fa";
import { FaCalendarAlt } from "react-icons/fa";
import UserDataService from '../../api/UserDataService';



class DynamicHeaderComponent extends Component {
    state = {
        isUserLoggedIn : false,
        approved : false,
        
    }

    componentDidMount () {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn()
        this.setState({isUserLoggedIn : isUserLoggedIn})
        if (isUserLoggedIn) {
            let id = sessionStorage.getItem('id')
            UserDataService.getUserById(id)
            .then(response => {
                this.setState({approved : response.data.approved})

            })
            .catch(error => {
                console.log(error)
            })
            

        }
        
    }

    componentDidUpdate(){
        
    }
    
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn()
        
        


        
        


        
        

        let navItems = null

        if (isUserLoggedIn) {
            // eslint-disable-next-line default-case
            switch (sessionStorage.getItem('role')) {
                case 'admin': navItems =
                    <ul className="navbar-nav">
                        {isUserLoggedIn &&  <li><Link className="nav-link" to={"/Wellcome/"+ sessionStorage.getItem('id')}><FaHome /> Start</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/Lehrer"><FaUniversity /> Lehrer</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/Sekretariat"><FaUniversity /> Sekretariat</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/alle"><FaUniversity /> Nutzer Verwalten</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                    </ul>
                    break;

                case 'secretary':
                    navItems =
                        <ul className="navbar-nav">
                            {this.state.approved && <li><Link className="nav-link" to={"/Wellcome/"+ sessionStorage.getItem('id')}><FaHome /> Start</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/verwalten/Lernende"><FaUniversity /> Lernende</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/verwalten/Eltern"><FaCalendarAlt /> Eltern</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/Klassen"><FaCalendarAlt /> Klassen</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                        </ul>
                    break;
                case 'parent':
                    navItems =
                        <ul className="navbar-nav">
                            {this.state.approved && <li><Link className="nav-link" to={"/Wellcome/"+ sessionStorage.getItem('id')}><FaHome /> Start</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/verwalten/Lernende"><FaUniversity /> Meine Kinder</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                        </ul>
                    break;
                    case 'teacher':
                    navItems =
                        <ul className="navbar-nav">
                            {this.state.approved && <li><Link className="nav-link" to={"/Wellcome/"+ sessionStorage.getItem('id')}><FaHome /> Start</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/verwalten/Lernende"><FaUniversity /> Meine Klassen</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                        </ul>
                    break;
                    case 'student':
                    navItems =
                        <ul className="navbar-nav">
                            {this.state.approved && <li><Link className="nav-link" to={"/Wellcome/"+ sessionStorage.getItem('id')}><FaHome /> Start</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/verwalten/Lernende"><FaUniversity /> Meine Klasse</Link></li>}
                            {this.state.approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                        </ul>
                    break;
            }
                    
        }


        return (


            <header>

                <nav className="navbar navbar-expand-md navbar-light bg-light navbg">
                    <div><a className="navbar-brand">School4You</a></div>
                    {navItems}

                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/registrieren">Registrieren</Link></li>}

                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}

                    </ul>
                </nav>


            </header>


        )
    }
}

export default withRouter(DynamicHeaderComponent);