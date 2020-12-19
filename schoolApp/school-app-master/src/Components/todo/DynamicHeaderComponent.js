import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import { withRouter } from 'react-router';
import AuthenticationService from './AuthenticationService'
import { FaHome } from "react-icons/fa";
import { FaUniversity } from "react-icons/fa";
import { FaCalendarAlt } from "react-icons/fa";



class DynamicHeaderComponent extends Component {
    render() {


        const isUserLoggedIn = AuthenticationService.isUserLoggedIn()
        let approved = true


        const role = 'admin'
        

        let navItems = null

        if (isUserLoggedIn) {
            // eslint-disable-next-line default-case
            switch (role) {
                case 'admin': navItems =
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to="/Wellcome/ali"><FaHome /> Start</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/Lehrer"><FaUniversity /> Lehrer</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/Sekretariat"><FaUniversity /> Sekretariat</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/verwalten/alle"><FaUniversity /> Nutzer Verwalten</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
                    </ul>
                    break;

                case 'secretary':
                    navItems =
                        <ul className="navbar-nav">
                            {approved && <li><Link className="nav-link" to="/Wellcome/ali"><FaHome /> Start</Link></li>}
                            {approved && <li><Link className="nav-link" to="/termine"><FaUniversity /> Lehrer</Link></li>}
                            {approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Eltern</Link></li>}
                            {approved && <li><Link className="nav-link" to="/termine"><FaCalendarAlt /> Termine</Link></li>}
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