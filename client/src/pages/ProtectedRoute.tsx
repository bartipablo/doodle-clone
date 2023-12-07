import { Navigate } from 'react-router-dom';
import { userAtom } from '../lib/user';
import { FC, ReactElement } from 'react';
import { useAtom } from 'jotai';

const ProtectedRoute: FC<{ children: ReactElement }> = ({ children }) => {
    const [user, _] = useAtom(userAtom);
    if (user == undefined) {
        return <Navigate to="/login" replace />;
    }
    return children;
};

export default ProtectedRoute;
