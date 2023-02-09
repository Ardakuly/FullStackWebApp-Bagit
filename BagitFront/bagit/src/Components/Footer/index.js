import React from "react";
import { Link } from "react-router-dom";

import "./index.css";

export const Footer = () => {

    return (
        <footer id="footer">

           <div id="footer-contacts">
            <div id="footer-contacts-logo">
                <img src="/logo.png" alt="logo" />
            </div>
            <div id="footer-contacts-options">
                <p>Сілтемелер </p>
                <div id="footer-contacts-options-logos">
                    <div id="footer-instagram">
                        <a href="https://www.instagram.com/ardaku1y/?next=%2F">
                            <img src="/instagram.png" alt="instagram logo" /> 
                        </a>
                    </div>
                    <div id="footer-youtube">
                         <a href="https://www.youtube.com/@Barlyk">
                            <img src="/youtube.png"  alt="youtube logo"/>
                        </a>
                    </div>
                    <div id="footer-linkedin">
                         <a href="https://www.linkedin.com/in/alisher-ardaku1y/">
                            <img src="/linkedin.png"  alt="linkedin logo"/>
                        </a>
                    </div>
                </div>
            </div>
           </div>
            <div id="footer-message">
                <p><span> © 2023 Бағыт</span>Барлық құқықтар қорғалған.</p>
            </div>
    
        </footer>
    );
}