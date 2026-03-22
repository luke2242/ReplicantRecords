import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import MusicLibrary from "./pages/MusicLibrary";
import AddNewAlbum from "./pages/AddNewAlbum";
import AddNewArtist from "./pages/AddNewArtist";
import Navbar from "./components/Navbar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import EditForm from "./components/EditForm";
import EditArtistForm from "./components/EditArtistForm";


const queryClient = new QueryClient();

function App() {

  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/music" element={<MusicLibrary />}></Route>
          <Route path="/addartist" element={<AddNewArtist />}></Route>
          <Route path="addalbum" element={<AddNewAlbum />}></Route>
          <Route path="/editalbum/:id" element={<EditForm />} />
          <Route path="editArtist/:id" element={<EditArtistForm/>}></Route>
        </Routes>
      </BrowserRouter>
    </QueryClientProvider>
  )
}

export default App;
