import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { extractVideoId } from "../../utilities/youtubeVideoId";
import { Header } from "../../Components/Header";

import "./index.css";

export const DetailsPage = () => {

    const {place} = useParams();

    const [value, setValue] = useState({});

    useEffect(() => {

        async function placeRequest() {

            try {

                const resultPlace = await fetch(`http://localhost:8081/bagit/v1/places/${place}`);

                const resultJson = await resultPlace.json();
                
                if (resultPlace.ok) {

                    resultJson.video = extractVideoId(resultJson.video);

                    setValue(resultJson);

                    return;

                }

                new Error("Could not fetch data from database: 404");

            } catch (error) {

                console.log(error);

            }
        }


        placeRequest();

    }, [place, setValue]);

    const [image, setImage] = useState("");

    useEffect(() => {

        async function imageRequest() {

            try {
        
                const result = await fetch(`http://localhost:8081/bagit/v1/images/${value.image.realImage}`);
        
                if (result.ok) {

                    const blob = await result.blob();
        
                    const imageUrl = URL.createObjectURL(blob);
        
                    setImage(imageUrl);
        
                    return;
        
                }
        
                throw new Error("Resource could not be found: 404");
                
        
            } catch(error) {
        
                console.log(error);
        
            }
        
        }

        imageRequest();

    }, [value]);

    const style = {

        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url(${image})`

    }

    console.log(image);

    return (

        <main style={style}>
            <Header/>
            <div className="image">
                <div className="video-information">
                    <div className="information">
                        <div className="information-text">
                            <h1>{value.name}</h1>
                            <p>{value.description}</p>
                        </div>

                        <a href={place.navigator} className="navigator">
                            <div className="left-to-right-effect">
                                
                            </div>
                            <div className="navigator-image">
                                <img src="/yandex.png" alt="yandex logo" />
                            </div>
                        </a>
                    </div>
                    <div className="video">
                        <iframe width="100%" height="100%"
                             src={`https://www.youtube.com/embed/${value.video}`}
                              frameborder="0" 
                              allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
                              allowfullscreen>
                         </iframe>
                    </div>
                </div>
            </div>   
        </main>
    );


}


/*

    useEffect(() => {
        
        async function getImage() {

            const result = await fetch(`http://localhost:8081/bagit/v1/images/${image.realImage}`);

            const blob = await result.blob();

            const imageUrl = URL.createObjectURL(blob);

            setData(imageUrl);
        }

        getImage();


    }, []);

*/


