CREATE FUNCTION bank.TRANSFER(from_user TEXT, to_user TEXT, amount BIGINT) RETURNS void AS $$
BEGIN
  UPDATE bank.BALANCE SET balance = balance + amount WHERE user_id = to_user;
  UPDATE bank.BALANCE SET balance = balance - amount WHERE user_id = from_user;
END;
$$ LANGUAGE plpgsql;