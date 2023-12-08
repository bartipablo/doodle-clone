import { FC, useRef, useState } from 'react';

const VoteModal: FC<{ id: number; onClose: () => void }> = ({
    id,
    onClose,
}) => {
    const [voteType, setVoteType] = useState<string>('AVAILABLE');

    const handleVote = (e: React.ChangeEvent<HTMLInputElement>) => {
        setVoteType(e.target.value);
    };

    const submitForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.stopPropagation();
    };
    return (
        <div
            className="absolute flex h-screen w-screen cursor-pointer items-center justify-center bg-stone-800 bg-opacity-50"
            onClick={onClose}
        >
            <div
                className="h-1/2 w-1/2 cursor-auto rounded-xl bg-stone-100"
                onClick={(e) => e.stopPropagation()}
            >
                <form onSubmit={submitForm}>
                    <input
                        onChange={handleVote}
                        type="radio"
                        name="type"
                        value="AVAILABLE"
                        checked={voteType === 'AVAILABLE'}
                    ></input>
                    <input
                        onChange={handleVote}
                        type="radio"
                        name="type"
                        value="NOT_AVAILABLE"
                        checked={voteType === 'NOT_AVAILABLE'}
                    ></input>
                    <input
                        onChange={handleVote}
                        type="radio"
                        name="type"
                        value="MAYBE"
                        checked={voteType === 'MAYBE'}
                    ></input>
                    <input
                        onChange={handleVote}
                        type="radio"
                        name="type"
                        value="PENDING"
                        checked={voteType === 'PENDING'}
                    ></input>
                </form>
                {voteType}
            </div>
        </div>
    );
};

export default VoteModal;
