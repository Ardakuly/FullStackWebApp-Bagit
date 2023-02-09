import React, { useState, useEffect } from "react";
import { Redirect, useHistory } from "react-router-dom";

import "./index.css";

export const New = () => {

    const history = useHistory();

    const [place, setPlace] = useState({
        name: "",
        description: "",
        videoLink: "",
        navigatorLink: "",
    });

    const [placeImage, setPlaceImage] = useState(null);


    function handleChange({ target }) {
        
        if (target.type === "file") {
            setPlaceImage(target.files[0]);
        } else {
            setPlace({
                ...place,
                [target.name] : target.value
            });
        }

    }

    function handleSubmit(event) {

        event.preventDefault();

        async function postRequest() {

            const data = new FormData();
            data.append("name", place.name);
            data.append("description", place.description);
            data.append("videoLink", place.videoLink);
            data.append("navigatorLink", place.navigatorLink);
            data.append("image", placeImage);

            console.log(data);

            try {
                const result = await fetch("http://localhost:8081/bagit/v1/places", {
                    method: "POST",
                    headers: {
                        
                    },
                    body: data
                });


                if (!result.ok) {

                    console.log("Error: Could not add place");

                }
            } catch(error) {
                console.log(error);
            }

        }

        postRequest();

        history.push("/modification");

    }




    return (

        <div className="new">
            <form className="new-form" onSubmit={handleSubmit} method="POST" encType="multipart/form-data" >
                <div className="new-form-inputs">
                    <input  id="name" type="text" name="name" value={place.name} placeholder="Жердің есімі:" onChange={handleChange} required />
                    <input  id="description" type="text" name="description" value={place.description} placeholder="Жердің сипаттамасы:" onChange={handleChange} required />
                    <input  id="videoLink" type="text" name="videoLink" value={place.videoLink} placeholder="Видеоға сілтеме:" onChange={handleChange} required />
                    <input  id="navigatorLink" type="text" name="navigatorLink" value={place.navigatorLink} placeholder="Навигатор сілтемесі:" onChange={handleChange} required />
                    <input  id="image" type="file" name="image" value={place.image} onChange={handleChange} placeholder="Жердің бейнесі:" required />
                    <button type="submit">Жерді тіркеу</button>
                </div>
            </form>
        </div>

    );

}
