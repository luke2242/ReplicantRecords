// global.d.ts

// This tells TypeScript about Vite-style env variables
declare global {
  interface ImportMetaEnv {
    // required environment variables
    VITE_API_URL: string;

    // optional environment variables (uncomment if needed)
    // VITE_OTHER_FLAG?: string;
    // [key: string]: string | boolean | number | undefined; // allows more dynamic VITE_ envs
  }

  interface ImportMeta {
    readonly env: ImportMetaEnv;
  }
}

// Make this file a module so TS treats it correctly
export {};