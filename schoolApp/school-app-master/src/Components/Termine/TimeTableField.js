import { extend } from 'jquery';
import React, { Component } from 'react';
import AppointmentDataService from '../../api/AppointmentDataService';

class TimeTableField extends Component {

    state={
        exists :false,
        appointment : {
            
        },
        week : 0
    }

    componentDidMount () {
        this.setState({week : this.props.week})

        

        AppointmentDataService.getAppointmentByClassIdANndDateAndSlot(this.props.classId, this.props.date, this.props.slot)
        .then(response => {
            console.log(response.data)
            if(response.data) {
                this.setState({exists : true, appointment : response.data})
            } else {
                this.setState({exists : false})
            }
        }).catch(error => {
            console.log(error.response.data)
        })
    }

    componentDidUpdate() {
        if(this.props.week !== this.state.week) {
            this.setState({week : this.props.week})
            AppointmentDataService.getAppointmentByClassIdANndDateAndSlot(this.props.classId, this.props.date, this.props.slot)
        .then(response => {
            console.log(response.data)
            if(response.data) {
                this.setState({exists : true, appointment : response.data})
            } else {
                this.setState({exists : false})
            }
        }).catch(error => {
            console.log(error.response.data)
        })

        }
    }
    
    render() {
        console.log(this.state.week)
        let classId = this.props.classId
        let date = this.props.date
        let slot= this.props.slot


        let appointment = 
        <div>
            <p><span class="text-monospace" >{this.state.appointment.subject}</span>, <span class="font-weight-lighter">{this.state.appointment.teacherName}</span> </p>
            

        </div>
            
        
        

        
        return (
            <th scope="row">
                {! this.state.exists && <span class="badge badge-info">----</span>}
                {this.state.exists && appointment}

            </th>
        )
    }
}

export default TimeTableField