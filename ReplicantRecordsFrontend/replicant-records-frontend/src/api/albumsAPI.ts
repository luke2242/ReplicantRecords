import { useQuery } from "@tanstack/react-query";
import type { Album } from "../types";
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;

const albumsUrl = `${API_URL}albums/album`;
const deleteAlbumUrl = `${API_URL}albums/`

// Gets all albums, and is rendered in Home page
export const getAlbums = () => {
    return useQuery<Album[]>({
        queryKey: ["albums"],
        queryFn: async () => {
            const res = await axios.get(albumsUrl);
            return res.data;
        },
    });
};

// Uses the albumID, and will delete the album when user presses the button
export const deleteAlbum = async (albumID: number) => {

    try {
        const response = await axios.delete(deleteAlbumUrl + albumID)
        // Alerts user that the album was deleted visually
        window.alert("Sucessfully removed album!");
        return response.data
    }

    catch (error) {

        console.log(error)

    }

}


