import React, { Component } from 'react'
import moment from 'moment'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import * as Yup from 'yup';

import UserDataService from '../../api/UserDataService.js'
import { FaGlassMartiniAlt } from 'react-icons/fa';
import axios from 'axios';




class SignInFormValidations extends Component {

    constructor(props) {
        super(props)
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            role : 'admin',
            bithDate: moment(new Date()).format('YYYY-MM-DD'),
            usedEmails : []
        }
    }

    componentDidMount() {
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
        console.log(user);

        UserDataService.createUser(user, role)
        .then(() => { 
            this.props.history.push(`/login`)
        })
        .catch(err => {
            //Handle your error here
            console.log(err.response);
        })
        
    }

    validate= (values) => {
         let errors = {}
        //  if(!values.firstName) {
        //     errors.firstName = 'Bitte Vorname eingeben'}
        // }
        // if(!values.lastName) {
        //     errors.lastName = 'Bitte Nachname eingeben'
        // }
        // if(!values.email) {
        //     errors.email = 'Bitte Email Adresse eingeben'
        // }
        // if(!values.password) {
        //     errors.password = 'Bitte Passwort eingeben'
        // }
        // if(!values.repeatPassword) {
        //     errors.repeatPassword = 'Bitte Passwort wiederholen'
        // }
        // if(!moment(values.birthDate).isValid()) {
        //     errors.birthDate= 'Ungultiges Datum'
        // }
        if(values.password !== values.repeatPassword) {
            errors.repeatPassword = 'Passworte stimmen nicht überein'
        }
        return errors;

        
        //return errors

    }
    

    render() {

        let { firstName, lastName, email, password,role, bithDate } = this.state

        let usedEmails = this.state.usedEmails
        let minDate = new Date();
        console.log(minDate)
        minDate.setFullYear(minDate.getFullYear() - 10);
        console.log(minDate)




        return (
            <div >
                <h1>Registrieren</h1>

                <div className="container">
                    <Formik
                    initialValues={{ 
                        firstName : firstName,
                        lastName : lastName,
                    
                        email : email,
                        password : password,
                        repeatPassword : '',
                        role : role,
                        bithDate : bithDate
                    }}
                    initialTouched={{ 
                        field: true,
                      }}
                      validateOnMount
                    onSubmit={this.onSubmit}
                    validate={this.validate}
                    validateOnChange={true}
                    validateOnBlur={true}
                    enableReinitialize={true}

                    validationSchema ={Yup.object().shape({
                        firstName: Yup.string()
                        .required("Bitte Vorname eintragen")
                        .max(20,"Zu lang")
                            .matches(/^[a-zA-Z]*$/,"Ziffer und leerzeichen nicht zulässig "),

                            
                            

                            lastName: Yup.string().required("Bitte Nachname eintragen")
                            ,

                            email : Yup.string().required("Bitte Email Adresse eintragen")
                            .lowercase()
                            .email("Bitte gultige Email Adresse Eintragen ***@***.***")
                            .notOneOf(usedEmails, 'Email Adresse schon vergeben')
                            ,
    
                            password : Yup.string()
                            .required("Bitte Password eintragen")
                            .min(8, "Password zu kurz, mindestens 8 Zeichen!")
                            .matches(/(?=.*[0-9])/,"Passwort muss mindest eine Ziffer enthalten "),

                            repeatPassword : Yup.string().required("Bitte Passwort wiederholen")
                            // .test('repeatedPasswordTest', 'Password nicht korrekt wiederholt',
                            // value => {
                                
                            //     return false
                            // })

                            //OR TO TEST REPEAT PASSWORD :   .oneOf([Yup.ref('password')])
                            ,
                            birthDate : Yup.date().required("Bitte Geburtsdatum eingeben")
                            .max(minDate, 'Benuter muss mindestens 10 Jahre Alt sein')
                            


    
    
    
    
                    })}

                    >
                        {
                            props => {
                                const {
                                    values,
                                    touched,
                                    errors,
                                    isSubmitting,
                                    handleChange,
                                    handleBlur,
                                    handleSubmit
                                } = props;

                                return (
                                    <Form >

                                <fieldset className="form-group">
                                    <label>Vorname</label>
                                    <Field  className="form-control" type="text" name="firstName" value ={values.firstName} placeholder="Vorname" />
                                    <ErrorMessage name="firstName" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Name</label>
                                    <Field className="form-control" type="text" name="lastName" placeholder="Nachname" />
                                    <ErrorMessage name="lastName" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Email Adresse</label>
                                    <Field 
                                    className="form-control" type="text" name="email" placeholder="example@example.com" />
                                    <ErrorMessage name="email" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Passwort</label>
                                    <Field className="form-control" type="password" name="password" placeholder="*********" />
                                    <ErrorMessage name="password" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Passwort Wiederholen</label>
                                    <Field className="form-control" type="password" name="repeatPassword" />
                                    <ErrorMessage name="repeatPassword" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Geburtsdatum</label>
                                    <Field className="form-control" type="date" name="birthDate" />
                                    <ErrorMessage name="birthDate" component="small" className="form-text text-danger"></ErrorMessage>
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Rolle</label>
                                    <Field className="form-control" as="select" name="role">
                                    <option key="admin" value="admin">admin</option>
                                    <option key ="secretary" value="secretary">sekretariat</option>
                                    <option key="teacher" value="teacher">lehrer</option>
                                    <option key = "student" value="student">Lernender</option>
                                    <option key = "parent" value="parent">Eltern</option>
                                    </Field>
                                    <ErrorMessage name="role" component="div" className="alert alert-warning"></ErrorMessage>
                                </fieldset>
                                <button type="submit" className="btn btn-success"
                                disabled={errors.firstName || errors.lastName ||errors.email ||errors.password ||errors.repeatPassword|| errors.birthDate  } 
                                >save
                                </button>
                            </Form>)

                            }
                            
                        }
                    </Formik>

                </div>
            </div>
        )


    }
}

export default SignInFormValidations