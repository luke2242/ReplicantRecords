import { useNavigate } from "react-router-dom";
import { getAlbums, deleteAlbum } from "../api/albumsAPI";
import { getArtists, deleteArtist } from "../api/artistAPI";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Typography } from "@mui/material";
import ButtonGroup from '@mui/joy/ButtonGroup';

function Home() {

    const navigate = useNavigate();

    const { isLoading: albumsLoading, error: albumsError, data: albumsData } = getAlbums();
    const { isLoading: artistLoading, error: artistError, data: artistData } = getArtists();

    if (albumsLoading || artistLoading) return <h1>Loading...</h1>;
    if (albumsError || artistError) return <h1>Error: {(albumsError || artistError)?.message}</h1>;

    return (
        <>
            <h2>Artists</h2>
            {artistData?.map((artist) => (
                <div key={artist.id}>
                    <Card style={{ marginBottom: "16px" }} className="card">
                        <CardContent>
                            <Typography gutterBottom variant="h5">Artist Name: {artist.artistName}</Typography>
                            <Typography variant="body2">Artist Year Formed:{artist.yearFormed}</Typography>
                            <Typography variant="body2">Artist ID:{artist.id}</Typography>
                            <br />
                            <ButtonGroup spacing="0.5rem" aria-label="spacing button group" className="alignButtons" >
                                <Button variant="contained" color="error" startIcon={<DeleteIcon />} onClick={() => deleteArtist(artist.id)}></Button>
                                <Button variant="contained" startIcon={<EditIcon />} onClick={() => navigate(`editArtist/${artist.id}`)}></Button>
                            </ButtonGroup>
                        </CardContent>
                    </Card>
                </div>
            ))}
            <h2>Albums</h2>
            {albumsData?.map((album) => (
                <div key={album.artistId}>
                    <Card style={{ marginBottom: "16px" }} className="card">
                        <CardContent>
                            <Typography gutterBottom variant="h5"><b>{album.title}</b> by {album.artistName}</Typography>
                            <Typography variant="body2">Genre: {album.genre}</Typography>
                            <Typography variant="body2">Release Year: {album.releaseYear}</Typography>

                            <br />
                            <ButtonGroup spacing="0.5rem" aria-label="spacing button group" className="alignButtons" >
                                <Button variant="contained" color="error" startIcon={<DeleteIcon />} onClick={() => deleteAlbum(album.id)}></Button>
                                <Button variant="contained" startIcon={<EditIcon />} onClick={() => navigate(`/editalbum/${album.id}`)}></Button>
                            </ButtonGroup>
                        </CardContent>
                    </Card>
                </div>

            ))}
        </>
    );
}

export default Home;