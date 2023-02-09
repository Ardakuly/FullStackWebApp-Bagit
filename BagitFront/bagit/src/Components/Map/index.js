import React from "react";

import "./index.css";

export const Map = () => {


    return ( 
        <div id="map">
            
            <div id="map-yandex-map">
                <iframe src="https://yandex.ru/map-widget/v1/?um=constructor%3A45796f308388f8ea25a767872c502870291f43b7a2332877ee4a6a165a9d7189&amp;source=constructor" width="90%" height="80%" frameborder="0" 
                style={{borderRadius:"10px", boxShadow: "1px 2px 3px 4px rgba(20,20,20,0.4)"}}>
                </iframe>
            </div>
            
        </div>
    );


}