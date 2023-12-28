import './index.css';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ProtectedRoute from './pages/ProtectedRoute';
import Home from './pages/Home';
import Login from './pages/Login';
import Navbar from './components/Navbar';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Room from './pages/Room';
import { Provider } from 'jotai';
import { ThemeProvider } from '@/components/theme-provider';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <BrowserRouter>
            <Provider>
                <QueryClientProvider client={queryClient}>
                    <Navbar />
                    <main className="mt-12 flex flex-1 justify-center">
                        <Routes>
                            <Route
                                path="/"
                                element={
                                    <ProtectedRoute>
                                        <Home />
                                    </ProtectedRoute>
                                }
                            />
                            <Route
                                path="/room/:id"
                                element={
                                    <ProtectedRoute>
                                        <Room />
                                    </ProtectedRoute>
                                }
                            />
                            <Route path="/login" element={<Login />} />
                        </Routes>
                    </main>
                </QueryClientProvider>
            </Provider>
        </BrowserRouter>
    </ThemeProvider>
);
