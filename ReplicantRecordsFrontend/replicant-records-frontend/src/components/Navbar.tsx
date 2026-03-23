import { Link } from "react-router-dom"
import { AppBar, Button, IconButton, Stack, Toolbar, Typography } from "@mui/material"
import AlbumIcon from '@mui/icons-material/Album';
import { Home } from "@mui/icons-material";
import "../App.css";

export default function Navbar() {

    return (
        <>
            <AppBar position="static">
                <Toolbar className="navbar">
                    <IconButton size="large" edge='start' color="inherit" aria-label="record">
                        <AlbumIcon />
                    </IconButton>
                    <Typography variant="h6" component='div' sx={{ flexGrow: 1 }}>REPLICANT RECORDS</Typography>
                    <Stack direction='row' spacing={2}>
                        <Button><Link to='/' className="navLink"><Home /></Link></Button>
                        <Button><Link to='/music' className="navLink"> Music </Link></Button>
                        <Button><Link to='/addartist' className="navLink"> Add New Artist </Link></Button>
                        <Button><Link to='/addalbum' className="navLink"> Add New Album </Link></Button>
                    </Stack>
                </Toolbar>
            </AppBar>
        </>
    )
}