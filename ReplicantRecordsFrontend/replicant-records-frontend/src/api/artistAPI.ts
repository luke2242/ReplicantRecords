import { useQuery } from "@tanstack/react-query";
import type { Artist } from "../types";
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;

const artistsUrl = `${API_URL}artists/all`;
const deleteArtistsUrl = `${API_URL}artists/`;

export const getArtists = () => {
    return useQuery<Artist[]>({
        queryKey: ["artist"],
        queryFn: async () => {
            const res = await axios.get(artistsUrl);
            return res.data;
        },
    });
};

// Uses the albumID, and will delete the album when user presses the button
export const deleteArtist = async (artistID: number) => {

    try {
        const response = await axios.delete(deleteArtistsUrl + artistID)
        // Alerts user that the album was deleted visually
        window.alert("Sucessfully removed artist!");
        return response.data
    }

    catch (error) {

        console.log(error)

    }

}