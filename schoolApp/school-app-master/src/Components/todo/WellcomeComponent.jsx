import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import HelloWorldService from '../../api/HelloWorldService.js'

class WellcomeComponent extends Component {

    constructor(props) {
        super(props)
        this.retrieveWellcomeMessage = this.retrieveWellcomeMessage.bind(this)
        this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this)
        this.handleError = this.handleError.bind(this)
        this.state = {
            wellcomeMessage : '',
            errorMessage : ''
        }
    }



    render() {
        return (
            <>
            <div>
                <h1>Willkommen</h1>
                <div className="container">
                    Willkommen {this.props.match.params.name}.
                </div>

         Verwalte dein Profil  <Link to="/termine">hier</Link>.

                <div className="container">
             <button onClick={this.retrieveWellcomeMessage} className="btn btn-success"></button>

                </div>
            
                     {this.state.wellcomeMessage}

                <div className="container">
                    {this.state.errorMessage}

                </div>

                        

            </div>

            </>

        )
    }

    retrieveWellcomeMessage() {
        HelloWorldService.executeHelloWorldVariableService(this.props.match.params.name)
            .then(response => this.handleSuccessfulResponse(response))
        .catch(error => this.handleError(error))
    }

    handleSuccessfulResponse(response) {
        this.setState({wellcomeMessage : response.data.message})

    }

    handleError(error){
       this.setState({errorMessage : error.response.data.message})
    }
}

export default WellcomeComponent 