import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import HeaderComponent from './HeaderComponent'
import LoginComponent from './LoginComponent'
import ListTodosComponent from './ListTodoComponent'
import FooterComponent from './FooterComponent'
import LogoutComponent from './LogoutComponent.jsx'
import WellcomeComponent from './WellcomeComponent.jsx'
import ErrorComponent from './ErrorComponent'
import TodoComponent from './TodoComponent'
import SignInComponent from './SignInComponent'


class TodoApp extends Component {
    render() {
        return (
            <div className="todoApp">

                <Router>
                    <HeaderComponent />

                    <Switch>

                        <Route path="/" exact component={LoginComponent} />
                        <Route path="/login" component={LoginComponent} />
                        <Route path="/registrieren" component={SignInComponent} />

                        <AuthenticatedRoute path="/wellcome/:name" component={WellcomeComponent} />
                        <AuthenticatedRoute path="/termine/:id" component={TodoComponent} />

                        <AuthenticatedRoute path="/termine" component={ListTodosComponent} />
                        <AuthenticatedRoute path="/logout" component={LogoutComponent} />
                        <Route component={ErrorComponent} />

                    </Switch>

                    <FooterComponent/>

                    



                </Router>

                {/*<LoginComponent />
               <WellcomeComponent />*/}

            </div>
        )
    }
}








// class LoginComponent extends Component {
//     constructor(props) {
//         super(props)
//         this.state = {
//             username: 'username',
//             password: '',
//             failed: false,
//             success: false
//         }
//     }

//     handleChange = (event) => {
//         console.log(event.target.name);
//         this.setState(
//             {
//                 [event.target.name]: event.target.value
//             }
//         )
//     }

//     loginClicked = () => {
//         if (this.state.username === 'ali' && this.state.password === 'jemel') {

//             AuthenticationService.registerSuccessfullLogin(this.state.username, this.state.password);
//             this.props.history.push(`/wellcome/${this.state.username}`)
//             //this.setState({success : true})
//             //this.setState({failed : false})
//         } else {
//             this.setState({ failed: true })
//             this.setState({ success: false })
//         }

//     }
//     render() {
//         return (
//             //TRUE && True returns the second term !!!!!!!!!!!!!!!!!!!!!!!!!!!!
//             <div>

//                 <h1>Login</h1>
//                 <div className="container">




//                     {this.state.failed && <div className="alert alert-warning">Invalid Creds</div>}
//                     {this.state.success && <div>Success Login</div>}

//                     {/*<ShowLoginSuccessMessage ShowSuccessMessage={this.state.success}/>*/}

//                     <form className="main-form">

//                         <div class="form-group">

//                             <label for="username">Name </label>
//                             <input class="form-control" type="text" name="username" value={this.state.username} onChange={this.handleChange}></input>

//                         </div>

//                         <div className="form-group">

//                             <label for="password">Password</label>
//                             <input className="form-control" type="password" name="password" value={this.state.password} onChange={this.handleChange}></input>

//                         </div>

//                         <button className="btn btn-primary " onClick={this.loginClicked}>Login</button>
//                     </form>


//                 </div>



//             </div>

//         )
//     }
// }



// function ShowLoginFailMessage(props) {
//     if(props.ShowFailMessage) {
//        return  <div>EPIC FAIL</div>
//     }
//     else return null
// }


// function ShowLoginSuccessMessage(props) {
//     if(props.ShowSuccessMessage) {
//        return  <div>Login Success</div>
//     } 
//     else return null
// }


export default TodoApp