import { FC } from 'react';
import { Term } from '@/lib/response';
import dayjs from 'dayjs';

const CalendarTerm: FC<{ terms: Term[] }> = ({ terms: terms_ }) => {
    const terms = terms_.map((term) => {
        return { ...term, startDateTime: dayjs(term.startDateTime) };
    });
    terms.sort((a, b) => (a.startDateTime.isAfter(b.startDateTime) ? 1 : -1));
    const start = terms[0].startDateTime;
    let weeks = new Map<string, any>();
    terms.forEach((term) => {
        const monday = term.startDateTime.startOf('week').add(2, 'day');
        if (weeks.get(monday.toString()) === undefined) {
            weeks.set(monday.toString(), []);
        }
        weeks.get(monday.toString()).push(term);
    });
    console.log(weeks);
    return <></>;
};

export default CalendarTerm;
