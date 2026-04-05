import { fireEvent, render, renderHook, screen, waitFor, } from '@testing-library/react';
import '@testing-library/jest-dom/vitest';
import { MemoryRouter } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import AddNewAlbum from '../src/pages/AddNewAlbum';
import userEvent from '@testing-library/user-event';



// Bugs that were found
// An issue with artist ID input accepting letters as valid input - fixed by changing it from text to a number field 

const queryClient = new QueryClient();

const renderAddNewAlbumPage = () => {

    render(
        <QueryClientProvider client={queryClient}>
            <MemoryRouter>
                <AddNewAlbum />
            </MemoryRouter>
        </QueryClientProvider>
    );
}

describe("Test valid form inputs", () => {

    test("Valid artist and yearFormed", async () => {

        renderAddNewAlbumPage();

        const albumTitleInput = screen.getByLabelText("Album Title") as HTMLInputElement;
        const releaseYearInput = screen.getByLabelText("Release Year") as HTMLInputElement;
        const genreInput = screen.getByLabelText("Genre") as HTMLInputElement;
        const artistIDInput = screen.getByLabelText("Artist ID") as HTMLInputElement;

        const submitBtn = screen.getByRole('button', { name: /Add Album/i });

        // Inputs
        await userEvent.type(screen.getByLabelText("Album Title"), "Hello World");
        await userEvent.type(screen.getByLabelText("Release Year"), "1990");
        await userEvent.type(screen.getByLabelText("Genre"), "Metal");
        await userEvent.type(screen.getByLabelText("Artist ID"), "1");
        await userEvent.click(submitBtn);

        expect(albumTitleInput.value).toBe("Hello World");
        expect(releaseYearInput.value).toBe("1990");
        expect(genreInput.value).toBe("Metal");
        expect(artistIDInput.value).toBe("1");

    })
});