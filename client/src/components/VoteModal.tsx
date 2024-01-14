import { useAtomValue } from 'jotai';
import { FC, useState } from 'react';
import { userAtom } from '../lib/user';
import { serverUrl } from '../lib/data';
import {
    Card,
    CardContent,
    CardFooter,
    CardHeader,
    CardTitle,
} from './ui/card';
import { Button } from './ui/button';
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from './ui/select';

const fieldsetClass =
    'flex flex-col italic text-neutral-700 justify-center items-center text-xl';
const inputClass = 'aspect-square w-6';

const VoteModal: FC<{ id: number; onClose: () => void }> = ({
    id,
    onClose,
}) => {
    const userId = useAtomValue(userAtom);
    const [voteType, setVoteType] = useState<string | undefined>(undefined);

    const handleVote = (e: React.ChangeEvent<HTMLInputElement>) => {
        setVoteType(e.target.value);
    };

    const submitForm = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // FIXME: nie dziala
        const res = await fetch(`${serverUrl}/api/votes/add-vote`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                voteType,
                termId: id,
                attendeeId: +userId!,
            }),
        });
        window.location.reload();
        console.log(res);
        onClose();
    };

    return (
        <div
            className="absolute z-20 flex h-screen w-screen cursor-pointer items-center justify-center bg-black bg-opacity-90"
            onClick={onClose}
        >
            <form onSubmit={submitForm}>
                <Card
                    onClick={(e) => e.stopPropagation()}
                    className="cursor-auto"
                >
                    <CardHeader>
                        <CardTitle className="text-2xl">Vote</CardTitle>
                    </CardHeader>
                    <CardContent>
                        <Select onValueChange={(e) => setVoteType(e)}>
                            <SelectTrigger>
                                <SelectValue placeholder="Select availability" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="AVAILABLE">
                                    Available
                                </SelectItem>
                                <SelectItem value="NOT_AVAILABLE">
                                    Not Available
                                </SelectItem>
                                <SelectItem value="MAYBE">Maybe</SelectItem>
                                <SelectItem value="PENDING">Pending</SelectItem>
                            </SelectContent>
                        </Select>
                    </CardContent>
                    <CardFooter className="justify-end gap-5">
                        <Button
                            variant="secondary"
                            type="button"
                            onClick={onClose}
                        >
                            Cancel
                        </Button>
                        <Button type="submit" disabled={voteType === undefined}>
                            Vote
                        </Button>
                    </CardFooter>
                </Card>
            </form>
        </div>
    );
    return (
        <div
            className="absolute flex h-screen w-screen cursor-pointer items-center justify-center bg-stone-800 bg-opacity-95"
            onClick={onClose}
        >
            <div
                className="flex h-1/2 w-1/2 cursor-auto flex-col items-center justify-center rounded-xl bg-stone-100 px-4 py-8"
                onClick={(e) => e.stopPropagation()}
            >
                <h1 className="text-4xl font-bold">Vote</h1>
                <form
                    onSubmit={submitForm}
                    className="flex h-2/3 flex-1 flex-col items-center justify-around"
                >
                    <div className="grid grid-cols-2 grid-rows-2 gap-8">
                        <fieldset className={`${fieldsetClass}`}>
                            <input
                                onChange={handleVote}
                                type="radio"
                                name="type"
                                value="AVAILABLE"
                                className={`accent-emerald-600 ${inputClass}`}
                                checked={voteType === 'AVAILABLE'}
                            ></input>
                            <label>Available</label>
                        </fieldset>
                        <fieldset className={`${fieldsetClass}`}>
                            <input
                                onChange={handleVote}
                                type="radio"
                                name="type"
                                value="NOT_AVAILABLE"
                                className={`accent-rose-600 ${inputClass}`}
                                checked={voteType === 'NOT_AVAILABLE'}
                            ></input>
                            <label>Not Available</label>
                        </fieldset>
                        <fieldset className={`${fieldsetClass}`}>
                            <input
                                onChange={handleVote}
                                type="radio"
                                name="type"
                                value="MAYBE"
                                className={`accent-amber-600 ${inputClass}`}
                                checked={voteType === 'MAYBE'}
                            ></input>
                            <label>Maybe</label>
                        </fieldset>
                        <fieldset className={`${fieldsetClass}`}>
                            <input
                                onChange={handleVote}
                                type="radio"
                                name="type"
                                value="PENDING"
                                className={`accent-orange-600 ${inputClass}`}
                                checked={voteType === 'PENDING'}
                            ></input>
                            <label>Pending</label>
                        </fieldset>
                    </div>
                    <button className="mt-2 w-32 rounded-full bg-emerald-500 py-2 font-bold text-white">
                        Send Vote
                    </button>
                </form>
            </div>
        </div>
    );
};

export default VoteModal;
