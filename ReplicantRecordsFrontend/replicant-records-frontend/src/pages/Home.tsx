import { useNavigate } from "react-router-dom";
import { getAlbums, deleteAlbum } from "../api/albumsAPI";
import { getArtists, deleteArtist } from "../api/artistAPI";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

function Home() {

    const navigate = useNavigate();

    const { isLoading: albumsLoading, error: albumsError, data: albumsData } = getAlbums();
    const { isLoading: artistLoading, error: artistError, data: artistData } = getArtists();

    if (albumsLoading || artistLoading) return <h1>Loading...</h1>;
    if (albumsError || artistError) return <h1>Error: {(albumsError || artistError)?.message}</h1>;

    return (
        <>
            <h1>Home</h1>
            <h2>Artists</h2>
            {artistData?.map((artist) => (
                <div key={artist.id}>
                    <p>Artist Name: {artist.artistName}</p>
                    <p>Artist Year Formed:{artist.yearFormed}</p>
                    <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => deleteArtist(artist.id)}></Button>
                    <Button variant="contained" startIcon={<EditIcon/>} onClick={() => navigate(`editArtist/${artist.id}`)}></Button>
                </div>
            ))}
            <h2>Albums</h2>
            {albumsData?.map((album) => (
                <div key={album.artistId}>
                    <p><b>{album.title}</b> by {album.artistName}</p>
                    <p>Release Year: {album.releaseYear}</p>
                    <Button variant="outlined" startIcon={<DeleteIcon/>} onClick={() => deleteAlbum(album.id)}>Delete Album</Button>
                    <Button variant="contained" startIcon={<EditIcon/>} onClick={() => navigate(`/editalbum/${album.id}`)}>Edit Album</Button>
                </div>
            ))}
        </>
    );
}

export default Home;