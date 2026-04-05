import { fireEvent, render, renderHook, screen, waitFor, } from '@testing-library/react';
import '@testing-library/jest-dom/vitest';
import { MemoryRouter } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import AddNewArtist from '../src/pages/AddNewArtist';

// Bugs that were found
// An issue with form validation - fixed by adding HTMLFor and ID tags to labels and input

const queryClient = new QueryClient();

const renderAddNewArtistPage = () => {

    render(
        <QueryClientProvider client={queryClient}>
            <MemoryRouter>
                <AddNewArtist />
            </MemoryRouter>
        </QueryClientProvider>
    );
}

describe("Test valid form inputs", () => {

    test("Valid artist and yearFormed", async () => {

        renderAddNewArtistPage();

        const artistNameInput = screen.getByLabelText("Artist Name") as HTMLInputElement;
        const yearFormedInput = screen.getByLabelText("Year Formed") as HTMLInputElement;
        const submitBtn = screen.getByRole('button', { name: /Add Artist/i });

        // Inputs
        fireEvent.change(artistNameInput, { target: { value: "Hello World" } });
        fireEvent.change(yearFormedInput, { target: { value: "1990" } });
        fireEvent.click(submitBtn);

        expect(artistNameInput.value).toBe("Hello World");
        expect(yearFormedInput.value).toBe("1990");

    })
})

describe("Test invalid form input", () => {

    test("invalid yearFormed", async () => {

        renderAddNewArtistPage();

        const artistNameInput = screen.getByLabelText("Artist Name") as HTMLInputElement;
        const yearFormedInput = screen.getByLabelText("Year Formed") as HTMLInputElement;
        const submitBtn = screen.getByRole('button', { name: /Add Artist/i });

        // Inputs
        fireEvent.change(artistNameInput, { target: { value: "Hello World" } });
        fireEvent.change(yearFormedInput, { target: { value: "1990abc" } });
        fireEvent.click(submitBtn);

        expect(artistNameInput.value).toBe("Hello World");
        expect(yearFormedInput.validity.valid).toBe(false);

    })
})