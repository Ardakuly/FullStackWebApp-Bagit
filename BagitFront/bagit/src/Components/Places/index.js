import React, {useState, useEffect} from "react";

import { Place } from "./place";
import "./index.css";

export const Places = () => {

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


    }, [])

    return (

        <div id="places">
            <h2>Көрікті жерлер</h2>
            <div id="places-flex">
                {places.map(place => {
                    return (
                        <Place name={place.name} image={place.image} />
                    );
                })}
            </div>
        </div>

    );


}

/*
{places.array.forEach(place => {
                return (
                    <Place name={place.name} image={place.image} />
                );
            })
*/