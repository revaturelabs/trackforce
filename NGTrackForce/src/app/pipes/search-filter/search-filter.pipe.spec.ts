import { SearchFilterPipe } from './search-filter.pipe';

describe('Pipe: SearchFilterPipe', () => {
  let pipe: SearchFilterPipe;
  const testStringArr: string[] = ['GOOGLE', 'FaCeBooK', 'appLE', 'microSoft', 'airbnb', '##^&', 'Cucumber.io', 'rev@ture'];
  const testStringForeign: string[] = ['Газпром', '삼성', '华为'];

  beforeEach(() => {
    pipe = new SearchFilterPipe();
  })

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should match same string with different capitalization', function () {
    expect(pipe.transform(testStringArr, 'google')).toEqual(['GOOGLE']);
    expect(pipe.transform(testStringArr, 'FACEBOOK')).toEqual(['FaCeBooK']);
  });

  it('should match names that contains search value', function () {
    expect(pipe.transform(testStringArr, 'oo')).toEqual(['GOOGLE', 'FaCeBooK']);
  });

  it('should return the whole array when search term is blank', function ()  {
    expect(pipe.transform(testStringArr, '')).toEqual(testStringArr);
  });

  it('should return empty array when there is no match', function () {
    expect(pipe.transform(testStringArr, 'Flabalaba')).toEqual([]);
  });

  it('matches special character', function () {
    expect(pipe.transform(testStringArr, '##^&')).toEqual(['##^&']);
    expect(pipe.transform(testStringArr, 'Cucumber.io')).toEqual(['Cucumber.io']);
    expect(pipe.transform(testStringArr, 'rev@ture')).toEqual(['rev@ture']);
  });

  it('matches Korean characters', function () {
    expect(pipe.transform(testStringForeign, '삼')).toEqual(['삼성']);
  });

  it('matches Chinese characters', function () {
    expect(pipe.transform(testStringForeign, '华')).toEqual(['华为']);
  });

  it('matches Russian characters', function () {
    expect(pipe.transform(testStringForeign, 'ром')).toEqual(['Газпром']);
  });

});
