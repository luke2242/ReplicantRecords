import { useState, useEffect } from "react";
import type { Artist } from "../types";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";


function EditArtistForm() {
    // gets the album id from the URL
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const API_URL = import.meta.env.VITE_API_URL;

    const { data: artistData, isLoading, error } = useQuery<Artist>({
        queryKey: ["artist", id],
        queryFn: async () => {
            const res = await axios.get(`${API_URL}artists/${id}`);
            return res.data;
        },
        enabled: !!id
    });

    const [artist, setArtist] = useState<Artist>({
        id: 0,
        artistName: "",
        yearFormed: 0
    });

    // Pre-populates the form with current data
    useEffect(() => {
        if (artistData) {
            setArtist(artistData);
        }
    }, [artistData]);

    // Handle form input change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setArtist((prevArtist) => ({
            ...prevArtist,
            [name]: name === "yearFormed" || name === "id" ? Number(value) : value
        }));
    };

const submitHandler = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Sending artist data:", artist);  // Add this line

    try {
        const res = await axios.put(`${API_URL}artists/${artist.id}`, artist);
        console.log("Response status:", res.status, "Response data:", res.data);
        if (res.status === 200) {
            window.alert("Artist successfully updated!");
            navigate('/');
        } else {
            window.alert("Update failed: Server returned status " + res.status);
        }
    } catch (error) {
        console.error("Update error:", error);
        window.alert("Update failed. Check console for details.");
    }
};

    if (isLoading) return <h1>Loading...</h1>;
    if (error) return <h1>Error loading album data</h1>;

    return (
        <>
            <h1>Edit Album</h1>

            <form onSubmit={submitHandler}>
                <label>Artist Name</label>
                <br />
                <input type="text" placeholder="Enter artist name..." name="artistName" value={artist.artistName} onChange={handleChange} required></input>
                <br />
                <label>Year Formed</label>
                <br />
                <input type="number" placeholder="Enter Year Formed..." name="yearFormed" value={artist.yearFormed} onChange={handleChange} required></input>
                <br />
                <button type="submit">Update Album</button>
                <button type="button" onClick={() => navigate('/')}>Cancel</button>
            </form>
        </>
    )
}

export default EditArtistForm;