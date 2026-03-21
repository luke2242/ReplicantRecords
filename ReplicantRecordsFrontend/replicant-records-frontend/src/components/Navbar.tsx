import { Link } from "react-router-dom"

export default function Navbar() {

    return (
        <>
            <div className="navbar">
                <Link to='/'>Home </Link>
                <Link to='/music'> Music </Link>
                <Link to='/addartist'> Add New Artist </Link>
                <Link to='/addalbum'> Add New Album </Link>
            </div>
        </>
    )
}