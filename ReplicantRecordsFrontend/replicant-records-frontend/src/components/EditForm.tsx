import { useState, useEffect } from "react";
import type { Album } from "../types";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";


function EditForm(){
    // gets the album id from the URL
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const API_URL = import.meta.env.VITE_API_URL;

    const { data: albumData, isLoading, error } = useQuery<Album>({
        queryKey: ["album", id],
        queryFn: async () => {
            const res = await axios.get(`${API_URL}albums/${id}`);
            return res.data;
        },
        enabled: !!id
    });

    const [album, setAlbum] = useState<Album>({
        id: 0,
        title: "",
        releaseYear: 0,
        artistId: 0,
        artistName: ""
    });

    // Pre-populates the form with current data
    useEffect(() => {
        if (albumData) {
            setAlbum(albumData);
        }
    }, [albumData]);

    // Handle form input change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setAlbum((prevAlbum) => ({
            ...prevAlbum,
            [name]: name === "releaseYear" || name === "artistId" ? Number(value) : value
        }));
    };

    const submitHandler = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const res = await axios.put(`${API_URL}albums/${album.id}`, album)
            console.log("Album updated", res.data);
            window.alert("Album successfully updated!");
            navigate('/');
        }
        catch (error) {
            console.log("An error has occured:", error);
        }
    }

    if (isLoading) return <h1>Loading...</h1>;
    if (error) return <h1>Error loading album data</h1>;

    return (
        <>
            <h1>Edit Album</h1>

            <form onSubmit={submitHandler}>
                <label>Album Title</label>
                <br />
                <input type="text" placeholder="Enter title..." name="title" value={album.title} onChange={handleChange} required></input>
                <br />
                <label>Release Year</label>
                <br />
                <input type="number" placeholder="Enter Release Year..." name="releaseYear" value={album.releaseYear} onChange={handleChange} required></input>
                <br />
                <label>Artist ID</label>
                <br />
                <input type="number" placeholder="Enter Artist ID..." name="artistId" value={album.artistId} onChange={handleChange} required></input>
                <br />
                <button type="submit">Update Album</button>
                <button type="button" onClick={() => navigate('/')}>Cancel</button>
            </form>
        </>
    )
}

export default EditForm;