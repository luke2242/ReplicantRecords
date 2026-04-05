import React, { useState } from "react";
import type { Album } from "../types";
import axios from "axios";

function AddNewAlbum() {

    const API_URL = import.meta.env.VITE_API_URL;

    const [album, setAlbum] = useState<Album>({
        id: 0,
        title: "",
        releaseYear: 0,
        artistId: 0,
        artistName: "",
        genre: ""
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

        try {
            const res = await axios.post(`${API_URL}albums`, album)
            console.log("Album Added", res.data);
            window.alert("Album sucessfully added!");
        }
        catch (error) {
            console.log("An error has occured:", error);
        }
    }

    return (
        <>
            <h1>User will add new album here</h1>

            <form onSubmit={submitHandler}>
                <label htmlFor="albumTitle">Album Title</label>
                <br />
                <input id="albumTitle" type="text" placeholder="Enter title..." name="title" value={album.title} onChange={handleChange} required></input>
                <br />
                <label htmlFor="releaseYear">Release Year</label>
                <input id="releaseYear" type="number" placeholder="Enter Release Year..." name="releaseYear" value={album.releaseYear} onChange={handleChange} required></input>
                <br />
                <label htmlFor="genre">Genre</label>
                <input id="genre" type="text" placeholder="Enter genre..." name="genre" value={album.genre} onChange={handleChange} required></input>
                <br />
                <label htmlFor="artistId">Artist ID</label>
                <br />
                <input id="artistId" type="number" placeholder="Enter Artist ID..." name="artistId" value={album.artistId} onChange={handleChange} required></input>
                <br />
                <button type="submit">Add Album</button>
            </form>
        </>
    )
}

export default AddNewAlbum;