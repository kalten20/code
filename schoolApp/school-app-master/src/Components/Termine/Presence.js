import { extend } from 'jquery';
import React, { Component } from 'react';
import AppointmentDataService from '../../api/AppointmentDataService';
import PresenceDataService from '../../api/PresenceDataService';

class Presence extends Component {

    state={
        fieldStatus : 'CREATE',
        present : '',
        changed : false,
        oldPresence : '',

        newPresence : ''
        
    }

    componentDidMount() {

        PresenceDataService.getPresenceByAppointmentIdAndStudentId(this.props.appointmentId, this.props.studentId)
        .then(response => {
            console.log(response.data)
            //If null => no presence has been entered yet => create
            if(!response.data) {
                this.setState({fieldStatus : 'CREATE'})
            } else {
                this.setState({fieldStatus : 'EDIT' , toEdit : response.data, oldPresence : response.data.present, present : response.data.present})
            }
        }).catch(error => {
            console.log(error.response.data)
        })
    }

    present =()=> {
        this.setState({present : true,
        changed :true})
    }

    notPresent =()=> {
        this.setState({present : false,
        changed :true})
    }

    submitCreatePresence =()=> {
        console.log(this.state.present)
        this.setState({fieldStatus : 'EDIT', changed : false, oldPresence : this.state.present })
        let newPresence = {
            appointmentId : this.props.appointmentId, studentId : this.props.studentId, present : this.state.present
        }
        console.log(newPresence)
        PresenceDataService.createPresence(newPresence)
        .then(response=> {
            this.setState({toEdit : response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error.response.data)
        })
    }

    submitEditPresence =()=> {
        PresenceDataService.editPresence(this.state.toEdit.id, this.state.present)
        .then(response => {
            console.log(response.data)
            this.setState({toEdit : response.data, changed : false, oldPresence : this.state.present})
        }).catch(error => {
            console.log(error.response.data)
        })
        

    }

    dismissEdit =()=> {
        this.setState({present : this.state.oldPresence, changed : false})
    }

  

   

    render() {

        



        let dropdown = 
        <div class="dropdown">
        <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Präsenz
</button>
        <div id="myselect" class="dropdown-menu" aria-labelledby="dropdownMenu1">
            {(!(this.state.fieldStatus ==='EDIT' && this.state.oldPresence)) && <option class="dropdown-item" onClick={this.present} value={true}>Anwesend</option>}
            { (!(this.state.fieldStatus ==='EDIT' && !this.state.oldPresence)) &&<option  class="dropdown-item" onClick={this.notPresent} value={false}>nicht Anwesend</option>}
           
        </div>
    </div>

    let label = null
    if(this.state.present) {
        label = <p style={{ color: 'green' }} className="text-monospace" >Anwesend</p>
    } else {
        label = <p style={{ color: 'red' }} className="text-monospace">nicht anwesend</p>
    }

    // initially just display that no presence is set
    let initialCreateLabel = <p className="text-monospace" >Bitte eintragen</p>



        return (
            <th>
                {/* {this.props.studentId}
                {this.props.appointmentId} */}
                {dropdown}
                {this.state.fieldStatus==='CREATE' && (! this.state.changed) && initialCreateLabel}
                {(! (this.state.fieldStatus==='CREATE' && (! this.state.changed)) ) && label}
                
                { this.state.fieldStatus === 'CREATE' && <button disabled={! this.state.changed} onClick={this.submitCreatePresence}  type="button" class="btn btn-primary btn-sm">Speichern</button>}
                { this.state.fieldStatus === 'EDIT' && <button disabled={! this.state.changed} onClick={this.submitEditPresence}  type="button" class="btn btn-primary btn-sm">Ändern</button>}

                { this.state.fieldStatus === 'EDIT' && <button onClick={this.dismissEdit} disabled={! this.state.changed}  type="button" class="btn btn-danger btn-sm">x</button>}

            </th>
        )
    }
}

export default Presence