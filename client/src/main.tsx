import React from 'react';
import './index.css';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ProtectedRoute from './pages/ProtectedRoute';
import Home from './pages/Home';
import Login from './pages/Login';
import Navbar from './components/Navbar';

const router = createBrowserRouter([
    {
        path: '/',
        element: (
            <ProtectedRoute>
                <Home />
            </ProtectedRoute>
        ),
    },
    {
        path: '/login',
        element: <Login />,
    },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <Navbar />
        <main className="flex flex-1 items-center justify-center">
            <RouterProvider router={router} />
        </main>
    </React.StrictMode>
);
