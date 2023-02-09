import React, {useEffect, useState} from "react";

import { Link } from "react-router-dom";

export const Place = ({name, image}) => {

    const [data, setData] = useState();

    useEffect(() => {
        
        async function getImage() {

            try {

                const result = await fetch(`http://localhost:8081/bagit/v1/images/${image.realImage}`);
                
                if (result.ok) {

                    const blob = await result.blob();

                    const imageUrl = URL.createObjectURL(blob);

                    setData(imageUrl);

                    return;

                }

                throw new Error("Resource could not be found: 404");
                

            } catch(error) {

                console.log(error);

            }
        }

        getImage();
        console.log("Repeat");


    }, []);

    const [isHovered, setIsHovered] = useState(false);

    const handleMouseEnter = () => setIsHovered(true);
    const handleMouseLeave = () => setIsHovered(false);

    let style = {
        height: "90%",
        minWidth: "20%",
        marginRight: "0.4rem",
        marginLeft: "0.4rem",
        borderRadius: "0.3rem",
        display: "flex",
        justifyContent: "center",
        alignItems: "flex-end",
        boxShadow: "1px 2px 2px 4px rgba(20,20,20,0.4)",
        textDecoration: "none",
        backgroundImage: `url(${data})`,
        backgroundSize: "cover",
        
        
       
    };

    let styleInformation = {

        base: {
            width: "100%",
            height: "20%",
            backgroundColor: "#DDD0C8",
            color: "#323232", 
            borderRadius: "0 0 0.3rem 0.3rem",
            textAlign: "center",
            transition: "background-color 0.3s ease-in-out, color 0.3s ease-in-out"
        },

        hover: {

            backgroundColor:  "#323232",
            color: "#DDD0C8",

        }
    }

    return (
            <Link to={`/${name}`} style={style} 
            onMouseEnter={handleMouseEnter} 
            onMouseLeave={handleMouseLeave}>
            
                <div style={{...styleInformation.base, ...(isHovered && styleInformation.hover)}}>
                    <p>{name}</p>
                </div>
            </Link>
    );

}