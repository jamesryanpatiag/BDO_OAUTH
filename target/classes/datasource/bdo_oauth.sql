CREATE DATABASE bdo
WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'English_Philippines.1252'
LC_CTYPE = 'English_Philippines.1252'
CONNECTION LIMIT = -1;

CREATE SCHEMA bdo_oauth
AUTHORIZATION postgres;

CREATE TABLE bdo_oauth."user"
(
	user_id bigserial NOT NULL,
	user_name character varying(255) NOT NULL,
	user_first character varying(255) NOT NULL,
	user_middle character varying(255),
	user_last character varying(255) NOT NULL,
	user_email character varying(255) NOT NULL,
	user_password character varying(255),
	user_active smallint NOT NULL DEFAULT 1,
	user_ldap smallint NOT NULL DEFAULT 0,
	user_created timestamp with time zone NOT NULL,
	user_updated timestamp with time zone NOT NULL,
	CONSTRAINT user_id PRIMARY KEY (user_id),
	CONSTRAINT user_name UNIQUE (user_name)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE bdo_oauth."user"
OWNER TO postgres;

CREATE TABLE bdo_oauth.app
(
	app_id bigserial NOT NULL,
	app_key integer NOT NULL,
	app_secret character varying(255) NOT NULL,
	app_hash character varying(255) NOT NULL,
	app_name character varying(255) NOT NULL,
	app_description character varying(255) NOT NULL,
	app_endpoint character varying(255) NOT NULL,
	app_active smallint NOT NULL DEFAULT 1,
	app_created timestamp with time zone NOT NULL,
	app_updated timestamp with time zone NOT NULL,
	CONSTRAINT app_id PRIMARY KEY (app_id),
	CONSTRAINT app_hash UNIQUE (app_hash),
	CONSTRAINT app_key UNIQUE (app_key),
	CONSTRAINT app_secret UNIQUE (app_secret)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE bdo_oauth.app
OWNER TO postgres;

CREATE TABLE bdo_oauth.client
(
	client_id bigserial NOT NULL,
	client_app bigserial NOT NULL,
	client_key integer NOT NULL,
	client_secret character varying(255) NOT NULL,
	client_name character varying(255) NOT NULL,
	client_description character varying(255) NOT NULL,
	client_uri character varying(255) NOT NULL,
	client_active smallint NOT NULL DEFAULT 1,
	client_created timestamp with time zone NOT NULL,
	client_updated timestamp with time zone NOT NULL,
	CONSTRAINT client_id PRIMARY KEY (client_id),
	CONSTRAINT client_client_app_fkey FOREIGN KEY (client_app)
	REFERENCES bdo_oauth.app (app_id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT client_key UNIQUE (client_key),
	CONSTRAINT client_secret UNIQUE (client_secret)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE bdo_oauth.client
OWNER TO postgres;

CREATE TABLE bdo_oauth.auth
(
	auth_id bigserial NOT NULL,
	auth_client bigserial NOT NULL,
	auth_user bigserial NOT NULL,
	auth_token character varying(255) NOT NULL,
	auth_code character varying(255) NOT NULL,
	auth_expire timestamp with time zone NOT NULL,
	auth_created timestamp with time zone NOT NULL,
	auth_updated timestamp with time zone NOT NULL,
	CONSTRAINT auth_id PRIMARY KEY (auth_id),
	CONSTRAINT auth_auth_user_fkey FOREIGN KEY (auth_user)
	REFERENCES bdo_oauth."user" (user_id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT auth_code UNIQUE (auth_code),
	CONSTRAINT auth_token UNIQUE (auth_token)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE bdo_oauth.auth
OWNER TO postgres;

CREATE TABLE bdo_oauth.session
(
	session_id bigserial NOT NULL,
	session_client bigserial NOT NULL,
	"session_user" bigserial NOT NULL,
	session_code character varying(255) NOT NULL,
	session_expire timestamp with time zone NOT NULL,
	session_created timestamp with time zone NOT NULL,
	CONSTRAINT session_id_pk PRIMARY KEY (session_id),
	CONSTRAINT session_session_client_fkey FOREIGN KEY (session_client)
	REFERENCES bdo_oauth.client (client_id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT session_code UNIQUE (session_code)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE bdo_oauth.session
OWNER TO postgres;
