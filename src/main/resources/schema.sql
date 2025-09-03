
CREATE TABLE public.registration (
    id integer NOT NULL,
    name  character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    electionName  character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    roles character varying(255) NOT NULL
);

ALTER TABLE public.registration OWNER TO root;

CREATE SEQUENCE public.registration_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registration_id_seq OWNER TO root;

ALTER SEQUENCE public.registration_id_seq OWNED BY public.registration.id;



ALTER TABLE ONLY public.registration ALTER COLUMN id SET DEFAULT nextval('public.registration_id_seq'::regclass);


 ALTER TABLE ONLY public.registration
     ADD CONSTRAINT registration_pkey PRIMARY KEY (id);