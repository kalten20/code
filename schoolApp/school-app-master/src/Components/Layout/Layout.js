import React from 'react';
import Aux from '../../hoc/Aux'
import classes from './Layout.css'
const layout = (props) => (

    <Aux>

        <div>Toolbar, sidebar, Toggelbar, backdrop</div>
        <main>{props.children}</main>




    </Aux>


)
export default layout
