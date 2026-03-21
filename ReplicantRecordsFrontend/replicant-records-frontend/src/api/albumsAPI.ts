import { useQuery } from "@tanstack/react-query";
import type { Album, Artist } from "../types";
import axios from "axios";
const API_URL = import.meta.env.VITE_API_URL;

const albumsUrl = `${API_URL}albums/album`;
const artistsUrl = `${API_URL}artists/all`;

// Gets all albums, and is rendered in Home page
export const getAlbums = () => {
    return useQuery<Album[]>({
        queryKey: ["albums"],
        queryFn: async () => {
            const res = await axios.get(albumsUrl);
            return res.data;
        },
        // Prevents unnecessary API calls by caching the data for 1 minute
        staleTime: 1000 * 60 * 1
    });
};

export const getArtists = () => {
    return useQuery<Artist[]>({
        queryKey: ["artist"],
        queryFn: async () => {
            const res = await axios.get(artistsUrl);
            return res.data;
        },
        // Prevents unnecessary API calls by caching the data for 1 minute
        staleTime: 1000 * 60 * 1
    });
};
