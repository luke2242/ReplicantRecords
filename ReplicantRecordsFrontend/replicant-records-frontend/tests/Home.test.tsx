import { render, renderHook, screen, waitFor } from '@testing-library/react';
import { getArtists } from '../src/api/artistAPI';
import Home from '../src/pages/Home';
import '@testing-library/jest-dom/vitest';
import { MemoryRouter } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import axios from 'axios';

vi.mock('axios');

const createWrapper = () => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: { retry: false },
    },
  });

  return ({ children }: { children: React.ReactNode }) => (
    <QueryClientProvider client={queryClient}>
      {children}
    </QueryClientProvider>
  );
};

describe("Home Page Loading", () => {
  test("Checks if the home page is loading", () => {
    const queryClient = new QueryClient();

    render(
      <QueryClientProvider client={queryClient}>
        <MemoryRouter>
          <Home />
        </MemoryRouter>
      </QueryClientProvider>
    );

    expect(screen.getByText(/Loading.../i)).toBeInTheDocument();
  });
});

describe("When our API call is successful for artists", () => {
  const mockAxios = vi.mocked(axios.get);

  const mockArtists = [
    { id: 0, artistName: "Hello", yearFormed: 2000 },
    { id: 1, artistName: "World", yearFormed: 2001 },
  ];

  beforeEach(() => {
    mockAxios.mockResolvedValue({ data: mockArtists });
  });

  it("Should return all our artists", async () => {
    const { result } = renderHook(() => getArtists(), {
      wrapper: createWrapper(),
    });

    // We wait for our API response and make sure that it returns the mock data
    await waitFor(() => {
      expect(result.current.data).toEqual(mockArtists);
      expect(result.current.isLoading).toBe(false);
      expect(result.current.error).toBeFalsy();
      
    });
  });
});

describe("When our API call is unsuccessful for artists", () => {
  const mockAxios = vi.mocked(axios.get);

  beforeEach(() => {
    mockAxios.mockRejectedValue(new Error("Failed to fetch artists"));
  });

  it("Should return an error", async () => {
    const { result } = renderHook(() => getArtists(), {
      wrapper: createWrapper(),
    });

    // We wait for our API response and make sure that it throws an Error
    await waitFor(() => {
      expect(result.current.data).toBeUndefined();
      expect(result.current.isLoading).toBe(false);
      expect(result.current.error).toBeInstanceOf(Error);
      
    });
  });
});