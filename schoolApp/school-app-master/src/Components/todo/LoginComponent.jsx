
import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import HeaderComponent from './HeaderComponent'

class LoginComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username: '',
            password: '',
            failed: false,
            success: false
        }
    }

    handleChange = (event) => {
        console.log(event.target.name);
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    loginClicked = () => {
        if (this.state.username === 'group4' && this.state.password === 'jemel') {

            AuthenticationService.registerSuccessfullLogin(this.state.username, this.state.password);
            this.props.history.replace(`/wellcome/${this.state.username}`)
            //this.setState({success : true})
            //this.setState({failed : false})
        } else {
            this.setState({ failed: true })
            this.setState({ success: false })
        }

    }
    render() {
        return (
            //TRUE && True returns the second term !!!!!!!!!!!!!!!!!!!!!!!!!!!!
            
            <div className="container-fluid bg">
                <div className="container"><h1>Login</h1></div>
                <div className="row">
                    <div className="col-md-4 col-sm-4 col-xs-12"></div>
                    <div className="col-md-4 col-sm-4 col-xs-12">
                        <form className="form-container">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email addresse</label>
                                <input  class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" name="username" value={this.state.username} onChange={this.handleChange}></input>
                                <small id="emailHelp" class="form-text text-muted"></small>
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">Passwort</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="********" name="password" value={this.state.password} onChange={this.handleChange}></input>
                                <small id="emailHelp" class="form-text text-muted">Passwort vergessen?</small>

                            </div>

                            <button type="submit" class="btn btn-success btn-block bouton" onClick={this.loginClicked}>Submit</button>
                        </form>

                    </div>
                    <div className="col-md-4 col-sm-4 col-xs-12"></div>
                </div>
            </div >





        )
    }
}

export default LoginComponent