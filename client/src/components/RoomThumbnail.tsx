import { useAtom } from 'jotai';
import { FC } from 'react';
import { Link } from 'react-router-dom';
import { userAtom } from '../lib/user';

const RoomThumbnail: FC<any> = ({ room }) => {
    const [user, _] = useAtom(userAtom);
    return (
        <li className="rounded-xl bg-slate-200 px-6 py-2">
            <Link
                to={`/room/${room.id}`}
                className="flex items-center justify-between"
            >
                <div>
                    <p className="text-xl font-bold">{room.title}</p>
                    <p>{room.description}</p>
                </div>
                <div>{room.owner.id == user && <span>Yours</span>}</div>
            </Link>
        </li>
    );
};

export default RoomThumbnail;
