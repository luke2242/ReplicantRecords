import { getAlbums, getArtists } from "../api/albumsAPI";

function Home() {

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
                </div>
            ))}
            <h2>Albums</h2>
            {albumsData?.map((album) => (
                <div key={album.artistId}>
                    <p><b>{album.title}</b></p>
                    <p>Release Year: {album.releaseYear}</p>
                    <p>Artist ID: {album.artistId}</p>
                </div>
            ))}
        </>
    );
}

export default Home;