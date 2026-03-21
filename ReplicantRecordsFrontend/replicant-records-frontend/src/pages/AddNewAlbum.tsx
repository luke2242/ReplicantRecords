import React, { useState } from "react";
import type { Album } from "../types";
import axios from "axios";

function AddNewAlbum() {

    const API_URL = import.meta.env.VITE_API_URL;

    const [album, setAlbum] = useState<Album>({
        title: "",
        releaseYear: 0,
        artistId: 0
    });

        // Handle form input change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setAlbum((prevAlbum) => ({
            ...prevAlbum,
            [name]: value
        }));
    };

    const submitHandler = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try{
        const res = await axios.post(`${API_URL}albums`, album)
        console.log("Album Added", res.data);
        }
        catch(error){
            console.log("An error has occured:", error);
        }
    }
    
    return (
        <>
            <h1>User will add new album here</h1>

            <form onSubmit={submitHandler}>
                <label>Album Title</label>
                <br />
                <input type="text" placeholder="Enter title..." name="title" value={album.title} onChange={handleChange}></input>
                <br />
                <label>Release Year</label>
                <br />
                <input type="number" placeholder="Enter Release Year..." name="releaseYear" value={album.releaseYear} onChange={handleChange}></input>
                <br />
                <label>Artist ID:</label>
                <br/>
                <input type="text" placeholder="Enter Artist ID..." name="artistId" value={album.artistId} onChange={handleChange}></input>
                <br/>
                <button type="submit">Add Album</button>
            </form>
        </>
    )
}

export default AddNewAlbum;