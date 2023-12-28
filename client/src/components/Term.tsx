import { FC, useState } from 'react';
import { Term as TermType } from '../lib/response';
import dayjs from 'dayjs';
import { createPortal } from 'react-dom';
import VoteModal from './VoteModal';
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from './ui/card';

const Term: FC<{ term: TermType }> = ({ term }) => {
    const [showModal, setShowModal] = useState(false);

    const startTime = dayjs(term.startDateTime);
    const endTime = startTime.add(term.duration, 'm');
    const startDate = startTime.format('DD.MM.YYYY');
    const startHour = startTime.format('HH:mm');
    const endHour = endTime.format('HH:mm');

    const available = term.votes.filter((v) => v === 'AVAILABLE').length;
    const notAvailable = term.votes.filter((v) => v === 'NOT_AVAILABLE').length;
    const maybe = term.votes.filter((v) => v === 'MAYBE').length;
    const pending = term.votes.filter((v) => v === 'PENDING').length;

    return (
        <>
            <Card
                className="flex flex-col items-center rounded bg-gray-200 px-2 py-4 text-xl font-medium"
                onClick={() => setShowModal(true)}
            >
                <CardHeader>
                    <CardTitle>{startDate}</CardTitle>
                    <CardDescription className="flex justify-between">
                        <span className="">{startHour}</span>
                        <span className="text-md">-</span>
                        <span className="">{endHour}</span>
                    </CardDescription>
                </CardHeader>
                <CardContent className="flex items-center gap-1 text-sm">
                    <span className="text-lg text-emerald-600">
                        {available}
                    </span>
                    /
                    <span className="text-lg text-red-600">{notAvailable}</span>
                    /<span className="text-lg text-amber-600">{maybe}</span>/
                    <span className="text-lg text-yellow-500">{pending}</span>
                </CardContent>
            </Card>
            {showModal &&
                createPortal(
                    <VoteModal
                        onClose={() => {
                            setShowModal(false);
                        }}
                        id={term.id}
                    />,
                    document.querySelector('#modal') as HTMLElement
                )}
        </>
    );
};

export default Term;
