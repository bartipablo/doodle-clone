import { useParams } from 'react-router-dom';

const Room = () => {
    const { id } = useParams();
    return <p>Room {id}</p>;
};

export default Room;
