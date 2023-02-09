import React from "react";

import { Banner } from "../../Components/Banner/index";
import { Introduction } from "../../Components/Introduction/index";
import { Map } from "../../Components/Map/index";
import { Places } from "../../Components/Places";
import { Header } from "../../Components/Header"

import "./index.css";

export const Home = () => {

    return (      
        <main>
            <div id="header-banner">
                <Header />
                <Banner />
            </div>
            <Introduction />
            <Places />
            <Map />
        </main>
    );


}