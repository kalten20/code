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
            firstname: '',
            lastname: '',
            email: 'example@example.com',
            password: '*********',
            bith: moment(new Date()).format('YYYY-MM-DD'),
            role: ''
        }
    }

    componentDidMount() {

    }

    onSubmit=(values)=> {
        let role = values.role
        let user = {
            firstname : values.firstname,
            lastname : values.lastname,
            email : values.email,
            password : values.password,
            birth: values.birth,
            role : values.role
        }
        UserDataService.createUser(user, role)
        {this.props.history.push(`/login`)}
        
        
    }


    render() {

        let { firstname, lastname, email, password, bith, role } = this.state


        return (
            <div>
                <h1>Registrieren</h1>

                <div className="container">
                    <Formik
                    initialValues={{
                        email : email,
                        password : password,
                        bith : bith
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
                                        <Field className="form-control" type="text" name="firstname" />
                                        <ErrorMessage name="firstname" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Name</label>
                                        <Field className="form-control" type="text" name="lastname" />
                                        <ErrorMessage name="lastname" component="div" className="alert alert-warning"></ErrorMessage>
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
                                        <Field className="form-control" type="date" name="birth" />
                                        <ErrorMessage name="birth" component="div" className="alert alert-warning"></ErrorMessage>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Rolle</label>
                                        <Field className="form-control" as="select" name="role">
                                        <option value="admin">Admin</option>
                                        <option value="sekretary">Sekretariat</option>
                                        <option value="professor">Lehrer</option>
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