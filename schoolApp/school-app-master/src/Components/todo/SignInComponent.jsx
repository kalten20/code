import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import AuthenticationService from './AuthenticationService'
import moment from 'moment'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import UserDataService from '../../api/UserDataService.js'



class SignInComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            firstName: 'HAMMA',
            lastName: 'HAMMA',
            email: 'example@example.com',
            password: 'HAMMA',
            role : 'admin',
            bithDate: moment(new Date()).format('YYYY-MM-DD')
        }
    }

    componentDidMount() {

    }

    onSubmit=(values)=> {
        let role = values.role
        let user = {
            firstName : values.firstName,
            lastName : values.lastName,
            email : values.email,
            password : values.password,
            role : values.role,
            birthDate: values.birthDate
        }
        UserDataService.createUser(user, role)
        //.then(() => {this.props.history.push(`/login`)})
        .catch(err => {
            //Handle your error here
            console.log(err.response);
        })
        
        
    }


    render() {

        let { firstName, lastName, email, password,role, bithDate } = this.state


        return (
            <div>
                <h1>Registrieren</h1>

                <div className="container">
                    <Formik
                    initialValues={{
                        firstName : firstName,
                        lastName : lastName,
                    
                        email : email,
                        password : password,
                        role : role,
                        bithDate : bithDate
                    }}
                    onSubmit={this.onSubmit}
                    validate={this.validate}
                    validateOnChange={false}
                    validateOnBlur={true}
                    enableReinitialize={true}

                    >
                        {
                            (props) => (


                                <Form>

                                    <fieldset className="form-group">
                                        <label>Vorname</label>
                                        <Field className="form-control" type="text" name="firstName" />
                                        <ErrorMessage name="firstName" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Name</label>
                                        <Field className="form-control" type="text" name="lastName" />
                                        <ErrorMessage name="lastName" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Email Adresse</label>
                                        <Field className="form-control" type="text" name="email" />
                                        <ErrorMessage name="email" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Passwort</label>
                                        <Field className="form-control" type="text" name="password" />
                                        <ErrorMessage name="password" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Passwort Wiederholen</label>
                                        <Field className="form-control" type="text" name="repeatpassword" />
                                        <ErrorMessage name="repeatpassword" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Geburtsdatum</label>
                                        <Field className="form-control" type="date" name="birthDate" />
                                        <ErrorMessage name="birthDate" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Rolle</label>
                                        <Field className="form-control" as="select" name="role">
                                        <option key="admin" value="admin">admin</option>
                                        <option value="sekretary">sekretariat</option>
                                        <option value="professor">lehrer</option>
                                        <option value="student">Lernender</option>
                                        </Field>
                                        <ErrorMessage name="role" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <button className="btn btn-success">save</button>
                                </Form>

                            )

                        }
                    </Formik>

                </div>
            </div>
        )


    }
}

export default SignInComponent