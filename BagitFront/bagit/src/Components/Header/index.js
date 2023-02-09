import React, {useState, useEffect} from "react";
import { NavLink } from "react-router-dom";

import "./index.css";

export const Header = ({ color }) => {

    const [scroll, setScroll] = useState(false);

    const style = {
        backgroundColor: "black",
        border: "0px",
        color: "rgba(160,174,192)"
    }

    const anchorStyle = {
        color: "rgba(160,174,192)"
    }

    function onScroll() {

        (window.scrollY >= 200) ? setScroll(true) : setScroll(false);

    }

    useEffect(() => {
        window.addEventListener("scroll", onScroll);

        return () => {
            window.removeEventListener("scroll", onScroll);
        };
    }, []);

    return (

        <header id="header" className={scroll ? "header header-bg header-new-color" : "header"} style={color ? style : {}}>
            <div id="logo">
                <img src="/logo.png" alt="" />
                <p>Бағыт</p>
            </div>
            <nav id="header-nav" >
                <NavLink to="#header" style={color ? anchorStyle : {}} activeClassName="active" className={ scroll && "header-new-color" } >Басты бет</NavLink>
                <NavLink to="#about" style={color ? anchorStyle : {}} activeClassName="active" className={ scroll && "header-new-color" }>Біз туралы</NavLink>
                <NavLink to="#map" style={color ? anchorStyle : {}} activeClassName="active" className={ scroll && "header-new-color" }>Карта</NavLink>           
            </nav>
        </header>

    );


}