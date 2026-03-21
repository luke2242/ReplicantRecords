import axios from "axios";
import { useState } from "react";
import type { Artist } from "../types";


function AddNewArtist() {

    const API_URL = import.meta.env.VITE_API_URL;
    
    const [artist, setArtist] = useState<Artist>({
        id: 0,
        artistName: "",
        yearFormed: 0
    });

        // Handle form input change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setArtist((prevArtist) => ({
            ...prevArtist,
            [name]: value
        }));
    };

    const submitHandler = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try{
        const res = await axios.post(`${API_URL}artists`, artist)
        console.log("Artist Added", res.data);
        }
        catch(error){
            console.log("An error has occured:", error);
        }
    }
    
    return (
        <>
            <h1>User will add new artist here</h1>

            <form onSubmit={submitHandler}>
                <label>Artist Name</label>
                <br />
                <input type="text" placeholder="Enter artist name..." name="artistName" value={artist.artistName} onChange={handleChange}></input>
                <br />
                <label>Year Formed</label>
                <br />
                <input type="number" placeholder="Enter Year Formed..." name="yearFormed" value={artist.yearFormed} onChange={handleChange}></input>
                <br />
                <button type="submit">Add Artist</button>
            </form>
        </>
    )
}

export default AddNewArtist;