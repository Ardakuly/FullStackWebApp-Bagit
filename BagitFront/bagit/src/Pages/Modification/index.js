import React, {useState, useEffect} from "react";
import { Route, Link} from "react-router-dom";
import { Header } from "../../Components/Header";
import { Place } from "../../Components/Places/place";
import { New } from "../../Components/ModificationNew";

import "./index.css";


export const Modification = () => {


    const [places, setPlaces] = useState([]);

    useEffect(() => {

        async function placeRequest () {

            try {
                const result = await fetch("http://localhost:8081/bagit/v1/places");

                const jsonResult = await result.json();
            
                if (result.ok) {

                    const realResult = jsonResult.map((place) => {
                        return ({
                            name: place.name,
                            image: place.image
                        });
                    });

                    setPlaces(realResult);
                    
                    return;
                } 

                throw new Error("Request failed: 404")
            } catch (error) {
                console.log(error);
            }
        }

        placeRequest();

    }, []);



    return (

        <main >

            <Header color = "black" />

            <Route path="/modification/new">
                <New />
            </Route>

            <div className="places">

                <div className="places-wrapper">
                    {places.map((place) => {
                        return (
                            <div className="place">
                                <Place name={place.name} image={place.image} />
                            </div>
                        );
                    })}
                    <Link to="./modification/new" className="places-new" >
                        +
                    </Link>
                </div>

            </div>
        </main>

    );

}