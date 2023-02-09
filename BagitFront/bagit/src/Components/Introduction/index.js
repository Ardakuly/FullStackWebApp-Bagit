import React, {useState, useEffect} from "react";

import "./index.css";

export const Introduction = () => {

    const [imgs, setImgs] = useState([0, 1]);

    const images = [
        "./banner1.jpg",
        "./banner2.jpg",
        "./random1.JPG",
        "./random2.JPG",
        "./random3.JPG"
    ];

    useEffect(() => {

        let id = setInterval(() => {
            setImgs([Math.floor(Math.random() * images.length), Math.floor(Math.random() * images.length)]);
        }, 15000);

        console.log("Interval is set -------------> +");

        return () => {

            clearInterval(id);
            console.log("Interval is cleared -------------> +");

        };
    }, []);

    return ( 
        <div id="introduction">
            
            <div id="images">
                <div id="img1">
                    <img src={images[imgs[0]]} alt="Travel Image 1" />
                </div>
                <div id="img2">
                    <img src={images[imgs[1]]} alt="Travel Image 2" />
                </div>
            </div>
            <div id="information">
                <h2><span>Актау қаласын</span> бізбен бірге саяхаттаңыз</h2>
                <p>тлпатфыоптыафоаптфұңціашітңшйтгаңтігңштагітгшіңтаіңгшітңгшіңтшгіңгңітаішңтіңгітңагаіңтшаіг</p>
                <p>ыватофатіңгштіңгштіңгштіңшгітңгіаңтшіңтгіңтіңтңішгтіаңгңітңітгңіштңігшітгаңтгіңштшңігтіңшгтіңгшңітгіңштіңгтңәгәңтңшәт</p>
            </div>
            
        </div>
    );


}